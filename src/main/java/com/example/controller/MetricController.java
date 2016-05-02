/**
 * 
 */
package com.example.controller;

import java.util.List;

import org.easyrules.api.RulesEngine;
import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.weightdetails.WeightDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Person;
import com.example.rules.WeightCheckRule;
import com.mongodb.MongoClient;


/**
 * @author Sampath
 *
 */
@Controller
public class MetricController 
{
	@RequestMapping("/person")
	public String person(Model model)
	{
		Person p = new Person();
		p.setFirstName("Sampath");
		p.setLastName("B");
		p.setAge(16);
		model.addAttribute("person", p);
		
		return "personview";
	}
	
	@ResponseBody
	@RequestMapping("/")
	String entry()
	{
		return "My Spring Boot App";
	}
	
	
	
	// METRIC API
	@RequestMapping("/process")//, method = RequestMethod.GET)
	@ResponseBody
    public String process() throws Exception {//@RequestBody String payload
        System.out.println("hiii");
        
        //adding rules
        WeightCheckRule firstDownRule   = new WeightCheckRule();
        RulesEngine rulesEngine = aNewRulesEngine().build();
        rulesEngine.registerRule(firstDownRule);
        firstDownRule.setWeightCheck("40");
        rulesEngine.fireRules();
        
        //saving data
        final Morphia morphia = new Morphia();
        morphia.mapPackage("org.mongodb.morphia.weightdetails");
        
     // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient("localhost", 27017), "morphia_example");
        datastore.ensureIndexes();
        
        WeightDetails wd = new WeightDetails();
        wd.setTimestamp("20160501113700");
        wd.setWeight("40.00");
        datastore.save(wd);
        
        return "hi";
    }
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@ResponseBody
    public String create(@RequestBody String jsonPayload) throws Exception {
        System.out.println("hiii");
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(jsonPayload);
        String timeStamp = (String) json.get("timeStamp");
        String weight = (String) json.get("value");
        
        final Morphia morphia = new Morphia();
        morphia.mapPackage("org.mongodb.morphia.example");
        
     // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "morphia_example");
        datastore.ensureIndexes();
        
        WeightDetails wd = new WeightDetails();
        wd.setTimestamp("20160501113700");
        wd.setWeight("40.00");
        datastore.save(wd);
        
        return "hi";
    }
	
	@RequestMapping("/read")//, method = RequestMethod.GET)
	@ResponseBody
    public String read() throws Exception {//@RequestBody String payload
        System.out.println("reading the data from mongo db");
        
        
        
        final Morphia morphia = new Morphia();
        morphia.mapPackage("org.mongodb.morphia.weightdetails");
        
     // create the Datastore connecting to the default port on the local host
        final Datastore datastore = morphia.createDatastore(new MongoClient("localhost", 27017), "morphia_example");
        datastore.ensureIndexes();
        
        final Query<WeightDetails> query = datastore.createQuery(WeightDetails.class);
        final List<WeightDetails> wdetails = query.asList();
        
        System.err.println("value at 0 index ::::::     "+wdetails.get(0));
        
        return "hi, timestamp:----"+wdetails.get(0).getTimestamp()+", weight value is:------"+wdetails.get(0).getWeight();
    }
}
