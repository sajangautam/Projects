package product;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Serving
{
	private Container container;
	private ArrayList<MixIn> topping = new ArrayList<>();
	private ArrayList<Scoop> scoop = new ArrayList<>();
	
	public Serving(Container container)
	{
		this.container = container;
		this.topping = topping;
		this.scoop = scoop;
	}
	
	public Serving(BufferedReader br) throws IOException
	{
		container = new Container(br);
		int lengthTopping = Integer.parseInt(br.readLine());
		int counterTopping = 0;
		while(counterTopping != lengthTopping)
		{
			topping.add(new MixIn(br));
			counterTopping += 1;
		}
		
		int lengthScoop = Integer.parseInt(br.readLine());
		int counterScoop = 0;
		while(counterScoop != lengthScoop)
		{
			scoop.add(new Scoop(br));
			counterScoop += 1;
		}
	}
	
	public void save(BufferedWriter bw) throws IOException
	{
		container.save(bw);
		bw.write("" + topping.size() + '\n');
		for(int i = 0; i < topping.size(); i ++)
		{
			topping.get(i).save(bw);
		}
		
		bw.write("" + scoop.size() + '\n');
		for(int i = 0; i < scoop.size(); i ++)
		{
			scoop.get(i).save(bw);
		}
	}
	
	public void addScoop(Scoop scoops)
	{
		scoop.add(scoops);
	}
	
	public void addTopping(MixIn toppings)
	{
		topping.add(toppings);
	}
	
	public Object[] scoops()
	{
		return scoop.toArray();
	}
	
	public int price()
	{
		int actualPrice = 0;
		for(Scoop scoops : scoop)
		{
			actualPrice += scoops.price();
		}
		
		for(MixIn toppings : topping)
		{
			actualPrice += toppings.price();
		}
		
		return actualPrice;
	}
	
	public int numScoops()
	{
		return scoop.size();
	}
	
	@Override
	public String toString()
	{
		String returnString = "";
		returnString += container + " ";
		returnString += (scoop.size() == 1)?"with a scoop of ":"with scoops of ";
		for(Scoop sc : scoop)
		{
			if(sc != null)
				returnString += sc + " ";
		}
		
		if(topping.size() > 0)
		{
			if(topping.size() == 1)
				returnString += "with topping ";
			else
				returnString += "with toppings ";
			String comma = "";
			for(MixIn topps : topping)
			{
				returnString += comma + topps;
				comma = ", ";
			}
		}
		return returnString;
	}
}