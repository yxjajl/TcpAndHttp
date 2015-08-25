package com.state;
public class NoQuarterState implements State {  
    private Machine machine;  
  
    public NoQuarterState(Machine machine) {  
        this.machine = machine;  
    }  
  
    @Override  
    public void insertQuarter() {  
        System.out.println("please insert a quarter!");  
        machine.setState(machine.getHasQuarterState());  
    }  
  
    @Override  
    public void ejectQuarter() {  
        System.out.println("please insert a quarter!");  
    }  
  
    @Override  
    public void turnCrank() {  
        System.out.println("please insert a quarter!");  
    }  
  
    @Override  
    public void dispense() {  
        System.out.println("please insert a quarter!");  
    }  
  
} 