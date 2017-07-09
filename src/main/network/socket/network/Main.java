package main.basic.socket.network;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by androidjp on 2016/11/15.
 */
public class Main {

    public static void main(String[] args){
        Server server = new Server();
        Client client1 = new Client(1);
        Client client2 = new Client(2);
    }
}

class Person implements Externalizable{
    public String name;
    public int age;
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.write(name.getBytes());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readLine();
    }
}
