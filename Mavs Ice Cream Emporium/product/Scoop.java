package product;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Scoop
{
	private IceCreamFlavor flavor;
	private ArrayList<MixIn> mixins = new ArrayList<>();
	
	public Scoop(IceCreamFlavor flavor)
	{
		this.flavor = flavor;
	}
	
	public Scoop(BufferedReader br) throws IOException
	{
		flavor = new IceCreamFlavor(br);
		int length = Integer.parseInt(br.readLine());
		int counter = 0;
		while(counter != length)
		{
			mixins.add(new MixIn(br));
			counter += 1;
		}
	}
	
	public void save(BufferedWriter bw) throws IOException
	{
		flavor.save(bw);
		bw.write("" + mixins.size() + '\n');
		for(int i = 0; i < mixins.size(); i ++)
		{
			if(mixins.get(i) != null)
				mixins.get(i).save(bw);
		}
	}
	
	public void addMixIn(MixIn mixin)
	{
		mixins.add(mixin);
	}
	
	public int price()
	{
		int actualPrice = flavor.price();
		for(MixIn mixin : mixins)
		{
			actualPrice += mixin.price();
		}
		return actualPrice;
	}
	
	@Override
	public String toString()
	{
		String finalReturn = "\"" + flavor;
		String helper = " with ";
		String comma = "";
		
		for(MixIn mix : mixins)
		{
			helper += comma + mix;
			comma += ", ";
		}
		
		return finalReturn + ((mixins.size() > 0)? helper + "\"" : "\"");
	}
}