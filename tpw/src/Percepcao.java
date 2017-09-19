/*CLasse que contém as percepções que cada sala contém */
public class Percepcao {
	private boolean stink;//fedor
	private boolean breeze;//brisa
	private boolean radiance;
	private boolean impact;
	private boolean cry;
	private String state;
	public Percepcao(){
		stink = false;
		breeze = false;
		radiance = false;
		impact = false;
		cry = false;
		state = " ";
	}
	public boolean isStink() {
		return stink;
	}
	public void setStink(boolean stink) {
		this.stink = stink;
	}
	public boolean isBreeze() {
		return breeze;
	}
	public void setBreeze(boolean breeze) {
		this.breeze = breeze;
	}
	public boolean isRadiance() {
		return radiance;
	}
	public void setRadiance(boolean radiance) {
		this.radiance = radiance;
	}
	public boolean isImpact() {
		return impact;
	}
	public void setImpact(boolean impact) {
		this.impact = impact;
	}
	public boolean isCry() {
		return cry;
	}
	public void setCry(boolean cry) {
		this.cry = cry;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void viewPerception(){
		System.out.println("stink  " + stink);
		System.out.println("breeze  " + breeze);
		System.out.println("radiance  " + radiance);
		System.out.println("impact  " + impact);
		System.out.println("cry   " + cry);
		System.out.println("state   "+ state);
	}
}
