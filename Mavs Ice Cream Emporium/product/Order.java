package product;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import product.Serving;
import person.Customer;

public class Order
{
	private ArrayList<Serving> servings = new ArrayList<>();
	private Customer customer;
	
	public Order(BufferedReader br) throws IOException
	{
		int length = Integer.parseInt(br.readLine());
		int counter = 0;
		while(counter != length)
		{
			servings.add(new Serving(br));
			counter += 1;
		}
		customer = new Customer(br);
	}
	
	public Order(Customer customer)
	{
		this.customer = customer;
	}
	
	public Order()
	{
		this.servings = servings;
		this.customer = customer;
	}
	
	public void save(BufferedWriter bw) throws IOException
	{
		bw.write("" + servings.size() + '\n');
		for(int i = 0; i < servings.size(); i ++)
		{
			servings.get(i).save(bw);
		}
		customer.save(bw);
	}
	
	public void addServing(Serving serving)
	{
		servings.add(serving);
	}
	
	public int price()
	{
		int actualPrice = 0;
		for(Serving serving : servings)
		{
			actualPrice += serving.price();
		}
		
		return actualPrice;
	}
	
	public Customer getCustomer()
	{
		return customer;
	}
	
	@Override
	public String toString()
	{
		String returnString = "Order for " + customer.toString() + " is ";
		String comma = "";
		for(Serving s : servings)
		{
			if(s != null)
			{
				returnString += comma + s;
				comma = ", ";
			}
		}
		return returnString + " at a price of $" + String.valueOf(this.price()) + ".";
	}
	
	public Object[] servings()
	{
		return servings.toArray();
	}
}