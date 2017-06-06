/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.linkmanager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Objects;

/**
 *
 * @author Cukier
 */
public class Data {
    long id;
    String name;
    String address;
    String direct;
    public Data(long id, String name, String address, String direct){
        this.id=id;
        this.name=name;
        this.address=address;
        this.direct=direct;
        
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Data)) {
            return false;
        }

        Data that = (Data) other;

        if (!this.name.equals(that.name)) {
            return false;
        }
        if (!this.address.equals(that.address)) {
            return false;
        }

        if (this.id != that.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.address);
        hash = 29 * hash + Objects.hashCode(this.direct);
        return hash;
    }
}
