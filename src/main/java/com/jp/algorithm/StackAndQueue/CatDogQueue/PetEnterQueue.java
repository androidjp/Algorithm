package com.jp.algorithm.StackAndQueue.CatDogQueue;

/**
 * pet队列
 * Created by androidjp on 16-8-9.
 */
public class PetEnterQueue {
    private Pet pet;//宠物
    private long count;///时间戳

    public PetEnterQueue(Pet pet, long count) {
        this.pet = pet;
        this.count = count;
    }

    public Pet getPet() {
        return pet;
    }

    public long getCount() {
        return count;
    }

    public String getPetType(){
        return this.pet.getPetType();
    }
}
