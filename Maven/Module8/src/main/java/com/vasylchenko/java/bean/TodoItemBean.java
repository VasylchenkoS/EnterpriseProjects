package com.vasylchenko.java.bean;

import com.vasylchenko.java.model.TodoItem;

import java.util.LinkedList;
import java.util.List;

public class TodoItemBean {

    private List<TodoItem> itemList;

    public TodoItemBean() {
        this.itemList = new LinkedList<>();
        itemList.add(new TodoItem("Cat1", "Name1", false));
        itemList.add(new TodoItem("Cat2", "Name2", true));
        itemList.add(new TodoItem("Cat3", "Name3", false));
    }

    public List<TodoItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<TodoItem> itemList) {
        this.itemList = itemList;
    }

}
