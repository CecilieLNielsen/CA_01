/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Joke;

/**
 *
 * @author rh
 */
public class JokeDTO {

    private String the_joke;
    private String type;
    private String reference;

    public JokeDTO(Joke joke) {
        this.the_joke = joke.getThe_joke();
        this.type = joke.getType();
        this.reference = joke.getReference();
    }
    
    
    
    

    public String getThe_joke() {
        return the_joke;
    }

    public void setThe_joke(String the_joke) {
        this.the_joke = the_joke;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

}
