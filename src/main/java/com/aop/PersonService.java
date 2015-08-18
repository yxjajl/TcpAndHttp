package com.aop;
public interface PersonService {
    public void save(String name);
    public void update(String name, Integer personId);
    public String getPersonName(Integer personId);
    public void exception() throws Exception;
}