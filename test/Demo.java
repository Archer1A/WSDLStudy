import com.xlauncher.bean.Book;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Demo {

    @Test
    public void toBook() throws DocumentException {
        SAXReader sr = new SAXReader();
        Document document = sr.read(new File("./test/book.xml"));
        Element root = document.getRootElement();
        List<Element> elementList = root.elements();
        List<Book> bookList = new ArrayList<Book>();
        for (Element e : elementList){
            Book book = new Book();
            book.setTitle(e.elementText("title"));
            book.setAuthor(e.elementText("author"));
            book.setYear(e.elementText("year"));
            book.setPrice(e.elementText("price"));
            book.setCategory(e.attributeValue("category"));
            bookList.add(book);
        }
        for (Book book : bookList){
            System.out.println("title:"+book.getTitle()+"\t category:"+book.getCategory()+"\t author:"+book.getAuthor()+"\t year:"+book.getYear()+"\t price:"+book.getPrice());
        }


    }

}
