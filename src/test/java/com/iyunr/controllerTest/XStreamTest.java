package com.iyunr.controllerTest;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class XStreamTest {

	private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                //@SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
	
	public static void main(String[] args) {
		//构建实体
		Person person = new Person();
		List<Address> listAdds = new ArrayList<Address>();
		Address add1 = new Address();
		add1.setStree("街道一号");
		add1.setHouseNo(1001);
		listAdds.add(add1);
		Address add2 = new Address();
		add2.setStree("街道二号");
		add2.setHouseNo(1002);
		listAdds.add(add2);
		person.setAdds(listAdds);
		person.setName("小明");
		person.setPhone("10086");
		
		String xml = toXml(person);
		System.out.println(xml);
		System.out.println("-----------------------------------------------------------------");
		
		Person p = toEntity(xml);
		System.out.println(p);
		System.out.println("-----------------------------------------------------------------");
		
		System.out.println(JSON.toJSONString(person));
		
	}
	
	public static Person toEntity(String xml){
		XStream xs = new XStream();
		//下面两句设置别名和注解@XStreamAlias("person")等效，用哪个都行，这里使用注解
//		xs.alias("person", Person.class);
//		xs.alias("address", Address.class);
		
		xs.setMode(XStream.NO_REFERENCES);
		
		Class<?>[] classes = new Class[] {Person.class, Address.class};
		XStream.setupDefaultSecurity(xs);
		xs.allowTypes(classes);
		
//		这句和@XStreamImplicit()等效  
//      xs.addImplicitCollection(Person.class,"addresses");  
//      这句和@XStreamAsAttribute()  
//      xs.useAttributeFor(Person.class, "name");  
		
		//注册使用了注解的实体
		xs.processAnnotations(classes);
		Person person = (Person) xs.fromXML(xml);
		return person;
	}
	
	public static String toXml(Person person){
//		XStream xs = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);
		//注册使用了注解的实体
		xstream.processAnnotations(new Class[]{Person.class, Address.class});
		String xml = xstream.toXML(person);
		return xml;
	}
	
}

//设置Person类在xml中的别名
@XStreamAlias("person")
class Person{
	//将name设置为XML person 元素的 attribute
	@XStreamAsAttribute()
	private String name;
	private String phone;
	//将此字段名在XML中去掉  
	@XStreamImplicit()
	private List<Address> adds = new ArrayList<Address>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Address> getAdds() {
		return adds;
	}
	public void setAdds(List<Address> adds) {
		this.adds = adds;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", phone=" + phone + ", adds=" + adds
				+ "]";
	}
	
	
}

@XStreamAlias("address")
class Address{
	private String stree;
	private int houseNo;
	public String getStree() {
		return stree;
	}
	public void setStree(String stree) {
		this.stree = stree;
	}
	public int getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}
	
	@Override
	public String toString() {
		return "Address [stree=" + stree + ", houseNo=" + houseNo + "]";
	}
	
}