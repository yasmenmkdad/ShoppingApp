package com.example.shoppingapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OnlineShoppingDataset extends SQLiteOpenHelper {
    private static String DatabaseName ="OnlineShopping26Database";
    SQLiteDatabase usersData;
    static String CurrentUsername="";
    public OnlineShoppingDataset(Context context) {
        super(context, DatabaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (username text primary key  ,password text not null , phone text ,email text,birthday Date  );");

        db.execSQL("create table store (idItem integer primary key AUTOINCREMENT, name text not null, sellername text , category text not null,description text ," +
                " AvailableinStock integer not null, price integer not null," +
                "barcode txt , imgpath text not null );");

        db.execSQL("create table cart (id integer primary key AUTOINCREMENT,username text not null , idItem int not null , numberOfItem integer ," +
                "FOREIGN KEY (idItem) REFERENCES store(idItem));");
        this.usersData = db;
        AddDataOnCreate();

        ContentValues newRow=new ContentValues();
        newRow.put("username","yasmen");
        newRow.put("password","1234");
        newRow.put("phone","01021454111");
        newRow.put("email","yasmen@yahoo.com");
        usersData.insert("user",null,newRow);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists cart");
        db.execSQL("drop table if exists store");
        onCreate(db);
    }

    public void AddData()
    {
        //SuperMarket
        insertItem( "Drinking Water","Aquafina","SuperMarket","Aquafina Purified Drinking Water 600ml",3f,300,"012000001574","https://i0.wp.com/eg.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/12/808271/2.jpg?w=740&ssl=1");
        insertItem("cocacolaZero","CocaCola","SuperMarket","cocacola Zero no sugar 350ml",4.5f,200,"5000112552232","https://www.pizzahut.mu/wp-content/uploads/2019/10/001445994_003_179081_708.jpg");
        insertItem("cocacolaCan","CocaCola","SuperMarket","cocacola 350ml",3.5f,200,"049000047608","https://freevector-images.s3.amazonaws.com/uploads/vector/preview/31728/32501_bigcoke.jpg");
        insertItem("nutella","nutella","SuperMarket","Nutella Chocolate Hazelnut Spread  750gm",50f,100,"3017620420702","https://images-na.ssl-images-amazon.com/images/I/815apQszWJL._SX425_.jpg");
    }

    public void AddDataOnCreate()
    {

        String[] names={"SuperMarket","Fashion","Beauty","Phone","Decor","Electronics","Baby care","Book","Pet Supplies"};
        //SuperMarket
        insertItemOnCreate( "Drinking Water","Aquafina","SuperMarket","Aquafina Purified Drinking Water 600ml",3f,300,"012000001574","https://i0.wp.com/eg.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/12/808271/2.jpg?w=740&ssl=1");
        insertItemOnCreate("cocacolaZero","CocaCola","SuperMarket","cocacola Zero no sugar 350ml",4.5f,200,"5000112552232","https://www.pngfind.com/pngs/m/161-1614737_coca-cola-zero-0-5-coca-cola-zero.png");
        insertItemOnCreate("cocacolaCan","CocaCola","SuperMarket","cocacola 350ml",3.5f,200,"049000047608","https://freevector-images.s3.amazonaws.com/uploads/vector/preview/31728/32501_bigcoke.jpg");
        insertItemOnCreate("nutella","nutella","SuperMarket","Nutella Chocolate Hazelnut Spread  750gm",50f,100,"3017620420702","https://i.insider.com/60622ae2c9d73b00186e9022?width=700");

        //Fashion
        insertItemOnCreate( "Sweatshirt Olive","H&M","Fashion","Hooded Top - Khaki green - Women",260f,30,"0","https://i.pinimg.com/originals/6e/bf/b2/6ebfb2cf668efcc7d16c4e640cf23a55.jpg");

        //Beauty
        insertItemOnCreate( "Foundation","Essence","Beauty","Essence Stay All Day 16H Long-Lasting Make-up Foundation",130f,30,"0","https://www.mazayastores.com/media/catalog/product/cache/74c1057f7991b4edb2bc7bdaa94de933/image/83781c46/essence-stay-all-day-16h-long-lasting-make-up-foundation.jpg");
        insertItemOnCreate( "coverstick","Essence","Beauty","Essence coverstick",60f,100,"0","https://www.mazayastores.com/media/catalog/product/cache/74c1057f7991b4edb2bc7bdaa94de933/image/749034b9/essence-coverstick.jpg");
        insertItemOnCreate( "Eyeshadow","Essence","Beauty","Essence Eyeshadow box, 05 dare to be bold!",130f,100,"0","https://www.mazayastores.com/media/catalog/product/cache/74c1057f7991b4edb2bc7bdaa94de933/image/801987ad/essence-be-an-original-eyeshadow-box-05-dare-to-be-bold.jpg");
        insertItemOnCreate( "Make Up Remover","Artdeco","Beauty","Artdeco Bi-Phase Make Up Remover",250f,70,"0","https://www.mazayastores.com/media/catalog/product/cache/74c1057f7991b4edb2bc7bdaa94de933/image/97758b29/artdeco-bi-phase-make-up-remover.jpg");
        insertItemOnCreate( "Nivea Cream ","Nivea","Beauty","Nivea Natural Fairness Face & Body Cream 200ml",45f,70,"0","https://cf5.s3.souqcdn.com/item/2019/03/27/80/23/29/7/item_XXL_8023297_96c84d6195620.jpg");
        insertItemOnCreate( "Make-up Remover","Garnier","Beauty","Garnier Micellar Water Face Eyes Lips Cleanser and Daily make-up Remover, 100ml",41f,20,"0","https://cf4.s3.souqcdn.com/item/2018/08/08/36/64/89/87/item_XXL_36648987_144513132.jpg");
        insertItemOnCreate( "Sun screen","Bioderma","Beauty","Bioderma Photoderm Max Spf 50+ Aquafluide Cream - 40 Ml",340f,200,"0","https://cf5.s3.souqcdn.com/item/2017/10/03/24/49/28/57/item_XXL_24492857_36037405.jpg");
        insertItemOnCreate( "Shampoo","L'Oreal","Beauty","Elvive Extraordinary Oil Shampoo for Normal to Dry Hair 200 ML",340f,200,"0","https://cf1.s3.souqcdn.com/item/2018/09/05/76/52/03/1/item_XXL_7652031_147584425.jpg");

        //Phone
        insertItemOnCreate( "Samsung Galaxy A21s","Samsung","Phone","Galaxy A21s SM-A217FZKGEGY Dual SIM Mobile - 6.5 Inch, 64 GB, 4 GB RAM, 4G LTE - Black",2740f,10,"0","https://cf1.s3.souqcdn.com/item/2020/06/11/13/09/88/59/5/item_XXL_130988595_95eb7ae087bce.jpg");
        insertItemOnCreate( "Xiaomi Redmi 9C","Xiaomi","Phone","Redmi 9C Dual SIM Mobile - 6.53 Inch, 64 GB, 3 GB RAM, 4G LTE - Midnight Gray",2120f,100,"0","https://cf5.s3.souqcdn.com/item/2020/09/10/13/17/79/02/8/item_XXL_131779028_dc71831a48022.jpg");
        insertItemOnCreate( "OPPO A15","OPPO","Phone","OPPO A15 Dual Sim Mobile - 6.5 Inches, 32 GB, 2 GB RAM, 4G LTE - Fancy White",2160f,100,"0","https://cf2.s3.souqcdn.com/item/2021/01/27/13/23/17/22/6/item_XXL_132317226_344a4fdfef693.jpg");

        //Decor
        insertItemOnCreate( " Wall painting","SASB","Decor"," Wall painting for home decor printed on a lightweight panel without a frame - Size 100 X 60 CM",850f,7,"0","https://cf5.s3.souqcdn.com/item/2018/08/09/37/01/44/66/item_XXL_37014466_144613160.jpg");
        insertItemOnCreate( "Arabic Chandelier","CLUC","Decor","Arabic Chandelier - antique Copper Gold",45f,70,"0","https://cf4.s3.souqcdn.com/item/2018/05/18/35/12/68/30/item_XXL_35126830_136714554.jpg");

        //Electronics
        insertItemOnCreate( "Micro-USB Type-B Cable","Belkin","Electronics","Belkin F2CU012BT2M-BLK 2 m USB Type-A to Micro-USB Type-B Cable",154f,14,"0","https://cf5.s3.souqcdn.com/item/2018/08/09/37/01/44/66/item_XXL_37014466_144613160.jpg");

        //Baby care
        insertItemOnCreate( "Feeding Bottle","Chicco","Baby care","Chicco Well-Being Feeding Bottle Boy Medium Flow 250ml - Silicone",166.49f,14,"0","https://cf2.s3.souqcdn.com/item/2018/10/23/30/03/44/40/item_XXL_30034440_156439916.jpg");

        //Book
        insertItemOnCreate( "Note Book","Mintra","Book","Mintra Econil 7 Lined Ruling A6 Note Book- 80 Sheets",17f,15,"0","https://cf1.s3.souqcdn.com/item/2018/06/10/34/89/79/88/item_XXL_34897988_139309298.jpg");

        //Pet Supplies
        insertItemOnCreate( "CAT DRY FOOD","Vita'day","Pet Supplies","1KG Vita'day CAT DRY FOOD",69f,15,"0","https://cf3.s3.souqcdn.com/item/2017/02/19/22/06/43/09/item_XXL_22064309_28709921.jpg");


    }

    //Table Cart
    public Cursor GetCartbyUsername(String username)
    {
        usersData = getReadableDatabase();
        String[] arg = {username};
        try {
            Cursor cursor = usersData.rawQuery("select idItem,numberOfItem,id from cart where username like ?", arg);
            cursor.moveToFirst();

            usersData.close();
            if(cursor.getCount() == 0)
                return null;
            else
                return cursor;
        }
        catch (Exception e)
        {
            usersData.close();
            return null;
        }
    }

    public void deleteItemIncart(int id) {
        String where = " id = ? ";
        String[] wherearg = {String.valueOf(id)};
        usersData=getWritableDatabase();
        usersData.delete("cart", where, wherearg);
//        Cursor cursor22 = usersData.rawQuery("select * from cart where id like ?", wherearg);

        usersData.close();
    }

    public void updateItemIncart(String username ,int idItem ,int numberOfItem ) throws Exception {

        Cursor cursor = GetCartbyUsername(username);
        int idmyitem;
        int totalitems = 0;
        ContentValues row = new ContentValues();
        int idCart = -1 ;
        if(cursor.getCount() != 0) //update
        {
            while (!cursor.isAfterLast()) {
                idmyitem = Integer.parseInt(cursor.getString(0));
                if(idItem == idmyitem)
                {
                    int oldNumofitem = Integer.parseInt(cursor.getString(1));
                    idCart = Integer.parseInt(cursor.getString(2));
                    totalitems = oldNumofitem + numberOfItem;
                }
                cursor.moveToNext();
            }
            if(idCart == -1)
            {
                throw new Exception("Not found");
            }
            if(totalitems == 0)
            {
                deleteItemIncart(idCart);
                return;
            }
            row.put("numberOfItem", totalitems );

            String where = " username = ? and idItem = ?";
            String[] wherearg = {username,String.valueOf(idItem)};
            usersData=getWritableDatabase();
            usersData.update("cart", row, where, wherearg);
            usersData.close();
        }
        else
            throw new Exception("Not found");
    }
    public void insertItemIncart(String username ,int idItem ,int numberOfItem )   {
        try
        {
            updateItemIncart(username ,idItem ,numberOfItem );
        }
        catch (Exception e)
        {
        ContentValues row = new ContentValues();
        row.put("username",username);
        row.put("idItem",idItem);
        row.put("numberOfItem",numberOfItem);
        usersData=getWritableDatabase();
        usersData.insert("cart",null,row);
        usersData.close();
        }
    }

    // Table store
    public Cursor fetchItemsbyCategory(String category)
    {
        usersData = getReadableDatabase();
        String[] arg = {category};
        Cursor cursor = usersData.rawQuery("select idItem, name,imgpath from store where category like ?", arg);
        cursor.moveToFirst();
        usersData.close();
        if(cursor.getCount() == 0)
            return null;
        else
            return cursor;

    }
    public int getItemCountinStore(int idItem)
    {
        usersData = getReadableDatabase();
        String[] arg = {String.valueOf(idItem)};
        Cursor cursor = usersData.rawQuery("select name, AvailableinStock from store where idItem like ?", arg);
        cursor.moveToFirst();
        usersData.close();
        if(cursor.getCount() == 0)
            return -1;
        else
            return Integer.parseInt(cursor.getString(1));
    }
    public void updateItemCountInstore(int idItem,int numberOfItem) throws Exception {
        int Totalitems = getItemCountinStore(idItem);
        ContentValues row = new ContentValues();
        int newitems = Totalitems - numberOfItem;
        if(newitems < 0)
            throw new Exception("Items needed is not The Available in stock ");
        row.put("AvailableinStock", newitems  );
        String where = " idItem = ? ";
        String[] wherearg = {String.valueOf(idItem)};
        usersData=getWritableDatabase();
        usersData.update("store", row, where, wherearg);
        usersData.close();
    }
    public Cursor fetchItemsbybarcode(String barcode)
    {
        usersData = getReadableDatabase();
        String[] arg = {barcode};
        String[] arg2 = {"nutella"};
        Cursor cursor2 = usersData.rawQuery("select * from store where name like ?", arg2);
        cursor2.moveToFirst();
        Cursor cursor = usersData.rawQuery("select idItem, name,imgpath from store where barcode like ?", arg);
        cursor2 = usersData.rawQuery("select * from store where name like ?", arg2);
        cursor.moveToFirst();
        usersData.close();
        if(cursor.getCount() == 0)
            return null;
        else
            return cursor;
    }
    public Cursor searchItemsbyId(int id)
    {
        usersData = getReadableDatabase();
        String[] arg = {String.valueOf(id)};
        Cursor cursor = usersData.rawQuery("select name, sellername ,description ,category, AvailableinStock, price, imgpath from store where idItem like ?", arg);
        cursor.moveToFirst();
        usersData.close();
        if(cursor.getCount() == 0)
            return null;
        else
            return cursor;
    }
    public Cursor searchItemsbyName(String name)
    {
        usersData = getReadableDatabase();
        String[] arg = {"%"+name+"%"};
        Cursor cursor = usersData.rawQuery("select idItem, name, sellername ,description ,category, AvailableinStock, price, imgpath from store where name like ?", arg);
        cursor.moveToFirst();
        usersData.close();
        if(cursor.getCount() == 0)
            return null;
        else
            return cursor;
    }



    private void insertItem(String name, String sellername, String category, String description,Float price, int NumProduct, String barcode, String img)
    {

        usersData = getReadableDatabase();
        String[] arg = {name};
        Cursor cursor = usersData.rawQuery("select * from item where name like ?", arg);
        cursor.moveToFirst();
        usersData.close();
        if(cursor.getCount() != 0)
            return;

        ContentValues row = new ContentValues();
        row.put("name",name);
        row.put("sellername",sellername);
        row.put("category",category);
        row.put("price",price);
        row.put("description",description);
        row.put("AvailableinStock",NumProduct);
        row.put("barcode",barcode);
        row.put("imgpath",img);
        usersData=getWritableDatabase();
        usersData.insert("store",null,row);
        usersData.close();
    }


    private void insertItemOnCreate(String name, String sellername, String category, String description,Float price, int NumProduct, String barcode, String img)
    {
        ContentValues row = new ContentValues();
        row.put("name",name);
        row.put("sellername",sellername);
        row.put("category",category);
        row.put("price",price);
        row.put("description",description);
        row.put("AvailableinStock",NumProduct);
        row.put("barcode",barcode);
        row.put("imgpath",img);
        usersData.insert("store",null,row);
    }


    //Table User
    public void AddNewUser(String username,String password ,String phone,String email,String birthday) {
        ContentValues newRow=new ContentValues();
        newRow.put("username",username);
        newRow.put("password",password);
        newRow.put("phone",phone);
        newRow.put("email",email);
        newRow.put("birthday",birthday);
        usersData=getWritableDatabase();
        usersData.insert("user",null,newRow);
        usersData.close();
    }
    public Boolean CheckUsernameEXISTS(String username) {

        usersData = getReadableDatabase();
        String[] arg = {username};
        Cursor cursor = usersData.rawQuery("select * from user where username like ?", arg);
        cursor.moveToFirst();
        usersData.close();

        if(cursor.getCount() == 0)
            return Boolean.FALSE;
        else
            return Boolean.TRUE;
    }
    public Boolean Checkpassword(String username,String password) {

        usersData = getReadableDatabase();
        String[] arg = {username};
        Cursor cursor = usersData.rawQuery("select password from user where username like ?", arg);
        cursor.moveToFirst();
        usersData.close();

        if(cursor.getString(0).toString().equals(password))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;

    }
    public Boolean CheckUserEmail(String username,String email) {

        usersData = getReadableDatabase();
        String[] arg = {username};
        Cursor cursor = usersData.rawQuery("select email from user where username like ?", arg);
        cursor.moveToFirst();
        usersData.close();

        if(cursor.getString(0).toString().equals(email))
            return Boolean.TRUE;
        else
            return Boolean.FALSE;

    }
    public void updateUserPassword(String username, String password ) {

        ContentValues row = new ContentValues();
        row.put("password", password );

        String where = " username = ? ";
        String[] wherearg = {username};
        usersData=getWritableDatabase();
        usersData.update("user", row, where, wherearg);
        usersData.close();

    }

    public void login(String username)
    {
        this.CurrentUsername = username;
    }
    public void logut()
    {
        this.CurrentUsername = "";
    }

    public static String getCurrentUsername() {
        return CurrentUsername;
    }

    // Temp
    public Cursor fetchbyname(String username) {

        usersData = getReadableDatabase();
        String[] arg = {username};
        Cursor cursor = usersData.rawQuery("select * from user where username like ?", arg);
        cursor.moveToFirst();
        usersData.close();
        return cursor;

    }



}
