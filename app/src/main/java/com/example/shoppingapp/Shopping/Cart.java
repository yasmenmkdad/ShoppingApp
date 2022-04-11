package com.example.shoppingapp.Shopping;

import java.util.ArrayList;

public class Cart {
    ArrayList<itemIncart> listCart;
    public Cart()
    {
        listCart=new ArrayList<itemIncart>() ;
    }
    public void AddItem(Item itemselected, int num)
    {
        itemIncart item=new itemIncart(itemselected,num);
        listCart.add(item);
    }

    public ArrayList<itemIncart> getListCart() {
        return listCart;
    }
}

class itemIncart
{
    Item item;
    Integer numberofitem;
    public itemIncart(Item item,int numberofitem)
    {
        this.item=item;
        this.numberofitem=numberofitem;
    }
}
