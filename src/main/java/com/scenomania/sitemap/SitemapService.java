package com.scenomania.sitemap;

import com.scenomania.dao.CityDao;
import com.scenomania.entities.City;
import com.scenomania.utils.UrlHelper;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.xml.transform.stream.StreamResult;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.castor.CastorMarshaller;
import org.springframework.stereotype.Service;

/**
 *
 * @author eugene
 */

@Service
public class SitemapService {
	
	@Autowired
	private CastorMarshaller marshaller;
	
	@Autowired 
	private ServletContext context;
	
	@Autowired 
	private CityDao cityDao;
	
	private Integer filecount = 0;
	
	private com.scenomania.sitemap.UrlSet sitemap = new UrlSet();
	
	private void writeXML() {
		
		filecount++;
		
		File filename = new File(context.getRealPath("/sitemap/sitemap"+Integer.toString(filecount)+".xml"));
		FileOutputStream os = null;
		
		try {
			os = new FileOutputStream(filename);
			marshaller.marshal(sitemap, new StreamResult(os));
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	public void process() {
		
		this.filecount = 0;
		
		marshaller.setSuppressXsiType(true);
		//marshaller.setSuppressNamespaces(true);
		
		Map<String, String> namespaces = new HashMap<String, String>();
		//namespaces.put("", "http://www.sitemaps.org/schemas/sitemap/0.9");
		//namespaces.put("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		//namespaces.put("schemaLocation", "http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd");
		
		marshaller.setNamespaceMappings(namespaces);
		
		List<City> cities = cityDao.fetchWhere(Restrictions.gt("population", 10000));
		Iterator<City> cit = cities.iterator();
		
		Integer urlcount = 0;
		this.sitemap = new UrlSet();
		
		while (cit.hasNext()) {
			City city = cit.next();
			
			com.scenomania.sitemap.Url url = new Url();
			try {
				url.setLoc("http://scenomania.ru" + UrlHelper.getUrl(city));
			} catch (Exception e) {
				continue;
			}
			
			sitemap.add(url);
			urlcount++;
			
			if (urlcount > 49990) {
				urlcount = 0;
				this.writeXML();
				this.sitemap = new UrlSet();
			}
		}
		
		if (urlcount > 0) this.writeXML();
		
		com.scenomania.sitemap.SitemapIndex index = new SitemapIndex();
		for (int i=1; i<= this.filecount; i++) {
			index.add(new Sitemap("http://scenomania.ru/sitemap/sitemap"+Integer.toString(i)+".xml"));
		}
		
		
		File filename = new File(context.getRealPath("/sitemap/sitemap.xml"));
		FileOutputStream os = null;
		
		try {
			os = new FileOutputStream(filename);
			marshaller.marshal(index, new StreamResult(os));
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

}
