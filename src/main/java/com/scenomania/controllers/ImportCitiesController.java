package com.scenomania.controllers;

import com.scenomania.entities.Area;
import com.scenomania.entities.AreaLocale;
import com.scenomania.entities.Band;
import com.scenomania.entities.BandPosition;
import com.scenomania.entities.City;
import com.scenomania.entities.CityLocale;
import com.scenomania.entities.Country;
import com.scenomania.entities.CountryLocale;
import com.scenomania.entities.User;
import com.scenomania.services.AreaService;
import com.scenomania.services.BandService;
import com.scenomania.services.CityService;
import com.scenomania.services.CountryService;
import com.scenomania.services.UserService;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author eugene
 */
@Controller
public class ImportCitiesController {

	@Autowired(required=true)
	private CountryService countryService;

	@Autowired(required=true)
	private AreaService areaService;

	@Autowired(required=true)
	private CityService cityService;

	@Autowired(required=true)
	private UserService userService;

	@Autowired(required=true)
	private BandService bandService;

	private void importCountries() {
		try {

			FileReader file = new FileReader("/home/eugene/Desktop/countries.txt");
			BufferedReader reader = new BufferedReader(file);

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] tmp = line.split(",");

				Map data = new HashMap();
				data.put("code", tmp[0]);
				data.put("name", tmp[1].replace("\"", ""));

				Country country = new Country();
				country.setFromHash(data);

				countryService.saveCountry(country);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void importRegions() {
		try {

			FileReader file = new FileReader("/home/eugene/Desktop/regions.txt");
			BufferedReader reader = new BufferedReader(file);

			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] tmp = line.split(",");

				Map data = new HashMap();
				data.put("countryCode", tmp[0]);
				data.put("code", tmp[1]);
				data.put("name", tmp[2].replace("\"", ""));

				Area area = new Area();
				area.setFromHash(data);

				areaService.saveArea(area);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void importCities() {
		try {

			FileReader file = new FileReader("/home/eugene/Desktop/cities.txt");
			BufferedReader reader = new BufferedReader(file);

			String ctr = "";

			String line = reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] tmp = line.split(",");

				City city = new City();

				StringUtils hlp = new StringUtils();
				
				city.setCountryCode(tmp[0].toUpperCase());
				city.setAreaCode(tmp[3].toUpperCase());
				city.setName(hlp.capitalize(tmp[1]));

				Integer pop = 0;
				try {
					pop = Integer.parseInt(tmp[4]);
				} catch (Exception e) {
					pop = 0;
				}
				
				city.setPopulation(pop);
				city.setReputation(0);
				city.setLatitude(Double.parseDouble(tmp[5]));
				city.setLongitude(Double.parseDouble(tmp[6]));

				cityService.saveCity(city);
				if (!ctr.equals(tmp[0])) {
					ctr = tmp[0];
					System.out.println(ctr);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String backTranslit(String name) {
		Map dph = new HashMap();

		dph.put("yo", "ё");
		dph.put("ya", "я");
		dph.put("yu", "ю");
		dph.put("ye", "е");
		dph.put("sh", "ш");
		dph.put("sch", "щ");
		dph.put("ch", "ч");
		dph.put("kh", "х");
		dph.put("zh", "ж");
		dph.put("ts", "ц");

		name = name.toLowerCase();

		for (int l = 3; l>0; l--) {

			Iterator itr = dph.keySet().iterator();
			while (itr.hasNext()) {
				Object key = itr.next();

				if (key.toString().length() == l) {
					name = name.replaceAll(key.toString(), dph.get(key).toString());
				}
			}
		}

		Pattern p = null;
		Matcher m = null;

		p = Pattern.compile("([bcdfghklmnprstvzшщчхжц])y");
		m = p.matcher(name);
		while (m.find()) {
			name = m.replaceFirst(m.group(1)+"ы");
			m = p.matcher(name);
		}

		p = Pattern.compile("^e");
		m = p.matcher(name);
		while (m.find()) {
			name = m.replaceFirst("э");
			m = p.matcher(name);
		}

		dph.clear();
		dph.put("a", "а");
		dph.put("b", "б");
		dph.put("v", "в");
		dph.put("g", "г");
		dph.put("d", "д");
		dph.put("z", "з");
		dph.put("i", "и");
		dph.put("k", "к");
		dph.put("l", "л");
		dph.put("m", "м");
		dph.put("n", "н");
		dph.put("o", "о");
		dph.put("p", "п");
		dph.put("r", "р");
		dph.put("s", "с");
		dph.put("t", "т");
		dph.put("u", "у");
		dph.put("f", "ф");
		dph.put("j", "ж");
		dph.put("'", "ь");
		dph.put("h", "х");
		dph.put("y", "й");
		dph.put("w", "в");

		Iterator itr = dph.keySet().iterator();
		while (itr.hasNext()) {
			Object key = itr.next();
			name = name.replaceAll(key.toString(), dph.get(key).toString());
		}

		name = StringUtils.capitalize(name);

		return name;
	}

	private void translateCities() {

		List<City> cities = cityService.fetchByCountryCode("RU");

		Iterator cit = cities.iterator();

		while (cit.hasNext()) {

			City city = (City) cit.next();

		String name = city.getName();

			name = backTranslit(name);

			List<CityLocale> locales = city.getLocales();

			CityLocale locale = new CityLocale();
			locale.setCity(city);
			locale.setName(name);
			locale.setLocale("ru");

			locales.add(locale);
			city.setLocales(locales);

			cityService.saveCity(city);

		}

	}

	private void translateAreas() {
		List<Area> areas = areaService.fetchByCountryCode("RU");

		Iterator ait = areas.iterator();

		while (ait.hasNext()) {
			Area area = (Area) ait.next();

			String name = area.getName();

			//System.out.print(name+" : ");

			name = backTranslit(name);

			Set<AreaLocale> locales = area.getLocales();

			AreaLocale locale = new AreaLocale();
			locale.setArea(area);
			locale.setLocale("ru");
			locale.setName(name);
			locales.add(locale);

			areaService.saveArea(area);

			//System.out.println(name);
			
		}
	}

	private void translateCountries() {
		List<Country> countries = countryService.fetchAll();

		Iterator cit = countries.iterator();

		while (cit.hasNext()) {
			Country country = (Country) cit.next();

			String name = country.getName();

			System.out.print(name+" : ");

			name = backTranslit(name);

			CountryLocale locale = new CountryLocale();
			locale.setName(name);
			locale.setLocale("ru");
			locale.setCountry(country);

			Set<CountryLocale> locales = country.getLocales();
			locales.add(locale);
			country.setLocales(locales);

			countryService.saveCountry(country);

			System.out.println(name);

		}

	}

	private void bandUser() {
		User user = userService.retrieveUser(1);

		Band band = new Band();
		band.setName("fried cucumbers");
		band.setHomecity(user.getHomecity());

		band = bandService.save(band);

		BandPosition position = new BandPosition();
		position.setBand(band);
		position.setUser(user);

		user.getPlayingIn().add(position);

		userService.saveUser(user);
	}

	private void indexCities() {
		List<City> cities = cityService.fetchAll();

		Iterator<City> cit = cities.iterator();

		while (cit.hasNext()) {
			City city = cit.next();
			Area area = areaService.getByCodes(city.getAreaCode(), city.getCountryCode());
			try {
				city.setAreaId(area.getId());
				cityService.saveCity(city);
			} catch (Exception e) {
				System.out.print(e);
			}
		}
	}
	
	@RequestMapping("/cities")
	public String index() {

		//importCities();
		//importRegions();
		//importCities();

		//translateCities();

		//translateAreas();
		//translateCountries();

		// bandUser();
		//indexCities();

		Iterator <Area> ait = areaService.fetchAll().iterator();

		while (ait.hasNext()) {
			Area area = ait.next();

			Country country = countryService.getByCode(area.getCountryCode());
			area.setCountryId(country.getId());
			areaService.saveArea(area);

		}

		return "";
	}
}
