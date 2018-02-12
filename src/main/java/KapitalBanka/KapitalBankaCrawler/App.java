package KapitalBanka.KapitalBankaCrawler;

import java.io.IOException;

import org.jsoup.Jsoup;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    	DB database = mongoClient.getDB("prvaDB");
    	DBCollection collection = database.getCollection("Valuti");
    	
    	 org.jsoup.nodes.Document doc = Jsoup.connect("http://www.capitalbank.com.mk/Web/%D0%97%D0%90_%D0%9D%D0%90%D0%A1/%D0%9A%D1%83%D1%80%D1%81%D0%BD%D0%B8_%D0%BB%D0%B8%D1%81%D1%82%D0%B8.aspx").get();

    	 org.jsoup.select.Elements rows = doc.select("tr");
         
         //org.jsoup.select.Elements date=doc.select("td.ns-kursna-home-datum");
         
    	 
    	 
    	 
         String jsonMsg = "[";

         for(org.jsoup.nodes.Element row :rows)
        
         {

             org.jsoup.select.Elements columns = row.select("td");
             
             int i = 1;
             
             jsonMsg = jsonMsg + "{";
          	
             for (org.jsoup.nodes.Element column:columns)

             {
            	 
                
                 if(i==1)
                 {
                 	jsonMsg = jsonMsg + "\"Земја\":";
                 	
                 	jsonMsg = jsonMsg + "\""+ column.text()+"\"";
                     
                 	jsonMsg = jsonMsg + ",";
                 	
                 	System.out.print(column.text());
                 }
//                 if(i==2)
//                 {
//                 	jsonMsg = jsonMsg + "\"Шифра\":";
//                 	
//                 	jsonMsg = jsonMsg + "\""+ column.text()+"\"";
//                     
//                 	jsonMsg = jsonMsg + ",";
//                 	
//                 	System.out.print(column.text());
//                 }
//                 if(i==3)
//                 {
//                 	jsonMsg = jsonMsg + "\"Важи за\":";
//                 	
//                 	jsonMsg = jsonMsg + "\""+ column.text()+"\"";
//                     
//                 	jsonMsg = jsonMsg + ",";
//                 	
//                 	System.out.print(column.text());
//                 }
                 if(i==4)
                 {
                 	jsonMsg = jsonMsg + "\"Куповен\":";
                 	
                 	jsonMsg = jsonMsg + "\""+ column.text()+"\"";
                     
                 	jsonMsg = jsonMsg + ",";
                 	
                 	System.out.print(column.text());
                 }
                 if(i==5)
                 {
                 	jsonMsg = jsonMsg + "\"Продажен\":";
                 	
                 	jsonMsg = jsonMsg + "\""+ column.text()+"\"";
                     
                 	jsonMsg = jsonMsg + ",";
                 	
                 	System.out.print(column.text());
                 }
//                 if(i==6)
//                 {
//                 	jsonMsg = jsonMsg + "\"Продажен\":";
//                 	
//                 	jsonMsg = jsonMsg + "\""+ column.text()+"\"";
//                     
//                 	
//                 	System.out.print(column.text());
//                 }

              i++;

             }
             
            
             
             jsonMsg = jsonMsg + "},";
             
             System.out.println();
              
         }
         jsonMsg = jsonMsg.substring(0, jsonMsg.length()-1);
         jsonMsg = jsonMsg + "]";

         System.out.print(jsonMsg);
    	 
     }
}
