package com.example.controller;


import java.util.ArrayList;
import java.util.List;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Contract;

@RestController
@RequestMapping("/api/demo")
public class Hello {
	
	
	@RequestMapping(value = "/demo", method = RequestMethod.GET)
    public List<Contract> demo(@RequestParam(value = "value", required = true) String value){
		List<Contract> list = new ArrayList<Contract>();
		Contract contract = new Contract();;
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		
        try {  
            //创建httpget  
            HttpGet httpget = new HttpGet("http://trade.china.cn/search/corp_"+value+".html");  
//            System.out.println("executing request " + httpget.getURI());  
            //执行get请求  
            CloseableHttpResponse response = httpclient.execute(httpget);  
            
            try {  
                //获取响应实体  
                HttpEntity entity = response.getEntity();  
                //响应状态  
//                System.out.println(response.getStatusLine());  
//                if(entity != null) {  
//                    //响应内容长度  
//                    System.out.println("response length: " + entity.getContentLength());  
//                    //响应内容  
//                    System.out.println("response content: " + EntityUtils.toString(entity));  
//                }  
                
              //[8]解析响应实体中的数据为字符串
                String result = EntityUtils.toString(entity, "gb2312");
                //解析json串
                Document document=Jsoup.parseBodyFragment(result);
//                JSONObject jsonObject=new JSONObject(document.text());
//                JSONArray jsonArray=new JSONArray(jsonObject.get("datas"));
//                for(int i=0;i<jsonArray.length();i++){
//                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
//                }
                String itemTag = "div[class=list-product list-company]";
                Elements elements = document.getElementsByClass("list-detail");
//                Elements elements = document.select(itemTag);
//                Object[]  obg = elements.toArray();
//                elements.size();
//                elements.get(index)
                for(Element eTmp : elements){
                	Document clearfixliDoc = Jsoup.parse(eTmp.toString());
                	if(null!=clearfixliDoc){
                		System.out.println(clearfixliDoc.toString());
                    	Elements test = clearfixliDoc.select("span a");
                    	
                    	System.out.println(test.get(0).attr("href"));
                    	contract.setCompany(test.get(0).text());
                    	contract.setPage(test.get(0).attr("href"));
                    	Document doc =getDoc(test.get(0).attr("href"));
                    	Elements elements2 = doc.getElementsByClass("contact-msg");
                    	Document leftListDoc = Jsoup.parse(elements2.get(0).toString());
//                    	System.out.println(leftListDoc.toString());
                    	Elements dldd = leftListDoc.select("li");
                    	
                    	for(Element dlddTmp : dldd){
                    		System.out.println(dlddTmp.text().replace("Contact", "").replace("Phone", "").replace("Address", ""));
                    		if(dlddTmp.text().indexOf("Contact")!=-1){
                    			contract.setName(dlddTmp.text().replace("Contact", ""));
                    		}
                    		if(dlddTmp.text().indexOf("Phone")!=-1){
                    			contract.setPhone(dlddTmp.text().replace("Phone", ""));
                    		}
                    		if(dlddTmp.text().indexOf("Address")!=-1){
                    			contract.setAddress(dlddTmp.text().replace("Address", ""));
                    		}
                    	}
                    	list.add(contract);
                	}
                	
                	
                }
                
//                elements.
//                while(elements.iterator().hasNext()){
//                	Element e = elements.iterator().next();
//                	System.out.println(e.text());
//                }
               
                //[9]销毁实体
                EntityUtils.consume(entity);
//                EntityUtils.consume(httpEntity);
                //[10]释放连接
//                httpPost.releaseConnection();
//                client.close();
                response.close();
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally {  
            //关闭链接,释放资源  
            try {  
                httpclient.close();  
            } catch(Exception e) {  
                e.printStackTrace();  
            }  
        }  
		
		return list;
	}
	
	
	
	
	public Document getDoc(String url) {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        Document doc =null;
        try {  
            //创建httpget  
            HttpGet httpget = new HttpGet(url);  
//            System.out.println("executing request " + httpget.getURI());  
            //执行get请求  
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                //获取响应实体  
                HttpEntity entity = response.getEntity();  
                //响应状态  
//                System.out.println(response.getStatusLine());  
//                if(entity != null) {  
//                    //响应内容长度  
//                    System.out.println("response length: " + entity.getContentLength());  
//                    //响应内容  
//                    System.out.println("response content: " + EntityUtils.toString(entity));  
//                }  
                
              //[8]解析响应实体中的数据为字符串
                String result = EntityUtils.toString(entity, "gb2312");
//                System.out.println(result);
                //解析json串
                doc=Jsoup.parseBodyFragment(result);

                response.close();
                return doc;
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally {  
            //关闭链接,释放资源  
            try {  
                httpclient.close();  
            } catch(Exception e) {  
                e.printStackTrace();  
            }  
        }
		return doc;  
    }

	

}
