/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceltest;

import java.text.SimpleDateFormat;
import java.util.*;


/**
 *
 * @author javalok
 */
public class ProductModel {
    
    public List<Product> findAll(){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            List<Product> result = new ArrayList<>();
            result.add(new Product("p1", "Name 1", 1000, 4, sdf.parse("2017-12-25")));
            result.add(new Product("p2", "Name 2", 3000, 5, sdf.parse("2018-11-21")));
            result.add(new Product("p3", "Name 3", 4000, 6, sdf.parse("2019-10-22")));
            result.add(new Product("p4", "Name 4", 5000, 7, sdf.parse("2015-9-1")));
            result.add(new Product("p5", "Name 5", 6000, 8, sdf.parse("2013-8-2")));
            result.add(new Product("p6", "Name 6", 6000, 8, sdf.parse("2013-8-2")));
            result.add(new Product("p7", "Name 7", 6000, 8, sdf.parse("2013-8-2")));
            result.add(new Product("p8", "Name 8", 6000, 8, sdf.parse("2013-8-2")));
            result.add(new Product("p9", "Name 9", 6000, 8, sdf.parse("2013-8-2")));
            result.add(new Product("p10", "Name 10", 6000, 8, sdf.parse("2013-8-2")));
            return result;
        }catch(Exception e){
        return null;
        }
    }
    
}
