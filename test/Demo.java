import com.xlauncher.bean.Book;
import org.dom4j.*;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
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
            for (Element a : e.elements()){
                System.out.println(a.getName());
            }
            System.out.println(e.getName());
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
    public  List<?> xmlmap(Class<?> cls) throws Exception {
        SAXReader sr = new SAXReader();
        Document document = sr.read(new File("./test/book.xml"));
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        List<Object> objectList = new ArrayList<Object>();

        for (Element e : elements) {
            Object obj = cls.newInstance();

            // Object object = new Object();
            for (Element a : e.elements()) {
                Field field = cls.getDeclaredField(a.getName());
                field.setAccessible(true);
                field.set(obj, a.getText());
            }
            objectList.add(obj);
        }

        return objectList;
    }


    public static void main(String[] args) {
        Demo demo = new Demo();
        try {
            List<Book> books = (List<Book>)demo.xmlmap(Book.class);
            for (Book book : books) {
                System.out.println("title:" + book.getTitle() + "\t category:" + book.getCategory() + "\t author:" + book.getAuthor() + "\t year:" + book.getYear() + "\t price:" + book.getPrice());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
