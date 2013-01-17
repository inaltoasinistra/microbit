package org.silix.the9ull.microbit.controlinterface;

//Java Bean
public class NyanCatJB {

	private int count;
	private String cat;
	private String rainbow;
	private String desc;
	private String colorDesc;
	
	final public String GRAY = "#C0C0C0";
	final public String WHITE = "#000000";
	
	public NyanCatJB() {
		setCount(2);
		setCat("<img src=\"http://the9ull.silix.org/imageupload/656d7aae/short-nyan.png\" />");
		setRainbow("<img src=\"http://the9ull.silix.org/imageupload/4faad3fe/rainbow.png\" />");
		setDesc("<small><font color=\"$COLOR\">Si dice Internet sia stata ideata per condividere foto di gatti.</font></small>");
		setColorDesc(GRAY);
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
		if(count>=8)
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
	
}
