/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazadanych;

/**
 *
 * @author Piotr
 */
public class Data {
    long id;
    String name;
    String adress;
    String direct;
    
    public Data(long id, String name, String adress, String direct){
        this.id=id;
        this.name=name;
        this.adress=adress;
        this.direct=direct;
        
    }
}