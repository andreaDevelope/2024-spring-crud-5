package org.java.spring_test_e_commerce;

import java.util.List;
import java.util.Optional;

import org.java.spring_test_e_commerce.db.pojo.Customer;
import org.java.spring_test_e_commerce.db.pojo.Orders;
import org.java.spring_test_e_commerce.db.pojo.Product;
import org.java.spring_test_e_commerce.db.service.CustomerService;
import org.java.spring_test_e_commerce.db.service.OrderService;
import org.java.spring_test_e_commerce.db.service.ProductService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringTestECommerceApplication implements CommandLineRunner{
	@Autowired
	private ProductService prs;

	@Autowired
	private CustomerService cs;

	@Autowired
	private OrderService os;


	public static void main(String[] args) {
		SpringApplication.run(SpringTestECommerceApplication.class, args);
	}

	@Override
	public void run(String... args){
		testNoRelations();
		testWhitRelations();
		System.out.println("END");
	}

	public void testNoRelations(){

		// CREATE CUSTOMERS
		Customer c1 = new Customer("Nome1", "Cognome1", "nome1cognome1@gmoail.ito", "1111111111111111");
		Customer c2 = new Customer("Nome2", "Cognome2", "nome2cognome2@gmoail.como", "222222222222222");

		cs.save(c1);
		cs.save(c2);

		// READ CUSTOMERS
		cs.getAll().forEach(System.out::println);

		// UPDATE CUSTOMER
		Optional<Customer> oldCOpt = cs.findCustById(c1.getId());

		if (oldCOpt.isEmpty()) {
			System.out.println("Custumer inesistente");
			return;
		}
				
		Customer oldC = oldCOpt.get();
		System.out.println(oldC);
				
		oldC.setName("Stella");
		oldC.setE_mail("stellacognome1@stellina.com");
				
		cs.save(oldC);
		System.out.println(c1 + " | aggiornato con successo in : " + oldC);

		// DELETE CUSTOMER
		cs.delete(c2);

		System.out.println("-------------------------------------------------------------------------");

		// CREATE Orders
		Orders o1 = new Orders(c1);
		Orders o2 = new Orders(c1);

		os.save(o1);
		os.save(o2);
		
		// READ ORDERS
		os.getAll().forEach(System.out::println);

		// DELETE ORDER
		os.delete(o1);

		System.out.println("---------------------------------------------------------------------------------");
		
		// CREATE PRODUCTS
		Product p1 = new Product("Prodotto1", 1000, 2300);
		Product p2 = new Product("Prodotto2", 1500, 2300);

		prs.save(p1);
		prs.save(p2);

		// READ PRODUCTS
		prs.getAll().forEach(System.out::println);

		// UPDATE PRODUCT
		Optional<Product> oldPOpt = prs.findProById(p1.getId());

		if (oldPOpt.isEmpty()) {
			System.out.println("Product inesistente");
			return;
		}
				
		Product oldP = oldPOpt.get();
		System.out.println(oldP);
				
		oldP.setName("NuovoProdotto1");
		oldP.setPrice(1300);
				
		prs.save(oldP);
		System.out.println(p1 + " | aggiornato con successo in : " + oldP);

		// DELETE PRODUCTS
		prs.delete(p2);

		System.out.println(p1.getFullPrice());

		System.out.println("-------------------------------------------------------------------------");
	}

	public void testWhitRelations(){
		// CREATED CUSTOMERS
		Customer c3 = new Customer("Maldi", "Testa", "homailditesta@gmoail.como", "3333333");
		Customer c4 = new Customer("Sonouna", "Stella", "sonounastella@gmoail.it", "4444444");
		Customer c5 = new Customer("Suonola", "Tromba", "suonolatromba@outlike.it", "555555555");

		cs.save(c3);
		cs.save(c4);
		cs.save(c5);

		// CREATED ORDERS
		Orders o3 = new Orders(c5);
		Orders o4 = new Orders(c5);
		Orders o5 = new Orders(c4);

		os.save(o3);
		os.save(o4);
		os.save(o5);

		// CREATED PRODUCTS
		Product p3 = new Product("prodotto3", 200000, 2300);
		Product p4 = new Product("prodotto4", 150000, 2300);
		Product p5 = new Product("prodotto5", 250000, 2300);

		prs.save(p3);
		prs.save(p4);
		prs.save(p5);

		// CREO LE MIE LISTE DI CUSTOMERS, ORDERS E PRODUCTS
		List<Customer> customers = cs.getAll();
		List<Orders> orders = os.getAll();
		List<Product> products = prs.getAll();

		// READ LE MIE LISTE
		customers.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		orders.forEach(System.out::println);
		System.out.println("-------------------------------------------------------");
		products.forEach(System.out::println);

		// RELAZIONO LA TABELLA PADRE ORDERS A PRODUCTS
		Optional<Orders> optO3 = os.getOrderByIdWithProducts(3);

		if (optO3.isEmpty()) {
			System.out.println("order con id 3 non trovato");
			return;
		}
		
		System.out.println("-------------------------------------------------------");
		
		o3 = optO3.get();
		o3.addProduct(p3);
		o3.addProduct(p4);
		os.save(o3);

		//RIMUOVO LA RELAZIONE TRA ORDER E PRODUCT
		o3.removeProduct(p4);
		os.save(o3);


		//FINALMENTE POSSO CANCELLARE I PRODUCT
		prs.delete(p4);

		System.out.println(p4 + "product cancellato");
	}
}
