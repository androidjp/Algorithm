package main.StackAndQueue.CatDogQueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列，用一个类实现猫+狗的一个各自队列操作
 * 思路：使用两个队列，和一个自定义类（宠物类型 + 时间戳），并用一个公共的时间戳来进行
 * Created by androidjp on 16-8-9.
 */
public class CatDogQueue {

    private Queue<PetEnterQueue> catQ;
    private Queue<PetEnterQueue> dogQ;
    private long count;////限制是这个count时间戳的最大值，也就是最多存储这么多个元素

    public CatDogQueue() {
        catQ = new LinkedList<PetEnterQueue>();
        dogQ = new LinkedList<PetEnterQueue>();
        this.count = 0;
    }

    public void add(Pet pet){
        if ("dog".equals(pet.getPetType())){
            this.dogQ.add(new PetEnterQueue(pet,count++));
        }else if ("cat".equals(pet.getPetType())){
            this.catQ.add(new PetEnterQueue(pet,count++));
        }else{
            throw new RuntimeException("不是dog 和 cat");
        }
    }

    /**
     * 出队
     * @return
     */
    public Pet pollAll(){
        if (!this.dogQ.isEmpty() && !this.catQ.isEmpty()){
            if (this.dogQ.peek().getCount()< this.catQ.peek().getCount()){///选择两个队列对头时间戳更小的那个,出队
                return this.dogQ.poll().getPet();
            }else{
                return this.catQ.poll().getPet();
            }
        }else if (!this.dogQ.isEmpty()){
            return this.dogQ.poll().getPet();
        }else if(!this.catQ.isEmpty()){
            return this.catQ.poll().getPet();
        }else {
            throw new RuntimeException("队列为空");
        }
    }

    public Dog pollDog(){
        if (this.dogQ.isEmpty())
            throw new RuntimeException("dogQueue is empty");

        return (Dog) this.dogQ.poll().getPet();
    }

    public Cat pollCat(){
        if (this.catQ.isEmpty())
            throw new RuntimeException("catQueue is empty");

        return (Cat) this.catQ.poll().getPet();
    }


    public boolean isEmpty(){

        return !(this.catQ.isEmpty()&&this.dogQ.isEmpty());
    }


}
