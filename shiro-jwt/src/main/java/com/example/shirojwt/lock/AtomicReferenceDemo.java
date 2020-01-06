package com.example.shirojwt.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;
@Getter
@ToString
@AllArgsConstructor
class User{
    String userName;
    int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {

        User u1=new User("z3",22);
        User u2=new User("l4",25);

        AtomicReference<User> atomicReference =new AtomicReference<>();
        atomicReference.set(u1);

        System.out.println(atomicReference.compareAndSet(u1, u2)+"\t "+atomicReference);
        System.out.println(atomicReference.compareAndSet(u1, u2)+"\t "+atomicReference);



    }
}
