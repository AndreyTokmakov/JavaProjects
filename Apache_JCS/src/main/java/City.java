//============================================================================
//Name        : City.java
//Created on  : Sep 04, 2017
//Author      : Tokmakov Andrey
//Version     : 1.0
//Copyright   : Your copyright notice
//Description : City class
//============================================================================

import java.io.Serializable;

public class City implements Serializable  {
	/** **/
    private static final long serialVersionUID = 6392376146163510146L;
    /** City name: **/
    private String name;

    /** Country  : **/
    private String country;

    /** Population of the city : **/
    private int population;

    /** City : **/
    public City( String name, String country, int population ) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    /** @method toString redefinition : **/
    @Override
    public String toString()  {
        return String.format("%s is a city in the country %s with a population of %d", name, country, population);
    }

	/**  @return the name : **/
	public String getName() {
		return name;
	}

	/** @param name the name to set : **/
	public void setName(String name) {
		this.name = name;
	}

	/**  @return the country : **/
	public String getCountry() {
		return country;
	}

	/**  @param country the country to set : **/
	public void setCountry(String country) {
		this.country = country;
	}

	/**  @return the population : **/
	public int getPopulation() {
		return population;
	}

	/** @param population the population to set : **/
	public void setPopulation(int population) {
		this.population = population;
	} 
}
