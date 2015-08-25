package com.state;
public class HasQuarterState implements State {  
    private Machine machine;  
      
    public HasQuarterState(Machine machine){  
        this.machine=machine;  
    }  
    @Override  
    public void insertQuarter() {  
        System.out.println("You can not insert another quarter!");  
    }  
  
    @Override  
    public void ejectQuarter() {  
        System.out.println("Quarter returned!");  
        machine.setState(machine.getNoQuarterState());  
    }  
  
    @Override  
    public void turnCrank() {  
        System.out.println("You turned ... ");  
        machine.setState(machine.getSoldState());  
    }  
  
    @Override  
    public void dispense() {  
        System.out.println("No gumball dispensed!");  
    }  
  
}  