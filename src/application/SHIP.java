package application;

public enum SHIP {

	BLUE("/ships/blue_ship.png", "/ships/blue_life.png"),
	GREEN("/ships/green_ship.png", "/ships/green_life.png"),
	ORANGE("/ships/orange_ship.png", "/ships/orange_life.png"),
	RED("/ships/red_ship.png", "/ships/red_life.png");
	
	private String urlShip;
	private String urlLife;
	
	private SHIP(String urlShip, String urlLife) {
		this.urlLife = urlLife;
		this.urlShip = urlShip;
	}
	 

	public String getUrl() {
		return this.urlShip;
	}
	
	public String getUrlLife() {
		return urlLife;
	}
}
