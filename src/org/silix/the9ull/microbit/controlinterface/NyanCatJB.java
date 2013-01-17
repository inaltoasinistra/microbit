package org.silix.the9ull.microbit.controlinterface;

//Java Bean
public class NyanCatJB {

	private int count, reset;
	private String cat;
	private String rainbow;
	private String desc;
	private String colorDesc;
	
	final public String GRAY = "#C0C0C0";
	final public String WHITE = "#FFFFFF";
	
	public NyanCatJB() {
		String cat30 = "1e2e42da/short-nyan30.png";
		String rainbow30 = "1243989b/rainbow30.png";
		String cat40 = "4f292a33/short-nyan40.png";
		String rainbow40 = "c287e5c1/rainbow40.png";
		String cat50 = "cf715711/short-nyan50.png";
		String rainbow50 = "1d3eb3a6/rainbow50.png";
		String cat70 = "656d7aae/short-nyan.png";
		String rainbow70 = "4faad3fe/rainbow.png";
		setCount(2);
		//setReset(8);
		setReset(12);
		setCat("<img src=\"http://the9ull.silix.org/imageupload/$PATH\" />".replace("$PATH", cat40));
		setRainbow("<img src=\"http://the9ull.silix.org/imageupload/$PATH\" />".replace("$PATH", rainbow40));
		setDesc("<small><font color=\"$COLOR\">Si dice Internet sia stata ideata per condividere foto di gatti.</font></small>");
		//setColorDesc(GRAY);
		setColorDesc(WHITE);
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increment() {
		System.out.println("Increment");
		count++;
		if(count>=getReset())
			count = 0;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getRainbow() {
		return rainbow;
	}

	public void setRainbow(String rainbow) {
		this.rainbow = rainbow;
	}

	public String getDesc() {
		return desc.replace("$COLOR", getColorDesc());
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getColorDesc() {
		return colorDesc;
	}

	public void setColorDesc(String colorDesc) {
		this.colorDesc = colorDesc;
	}

	public int getReset() {
		return reset;
	}

	public void setReset(int reset) {
		this.reset = reset;
	}
	
}
