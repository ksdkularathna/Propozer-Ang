package com.bargadss.Propozal.Dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.bargadss.Propozal.bean.City;
import com.bargadss.Propozal.bean.Country;
import com.bargadss.Propozal.bean.State;

public class CountryStateCityDao {

	Session session;

	public String listCountry(SessionFactory factory) {

		session = factory.openSession();
		JSONObject records = new JSONObject();

		try {
			List<Country> listData = session.createQuery("from Country").list();

			JSONArray record = new JSONArray();

			for (Country coun : listData) {
				record.put(coun.toJSON());
			}
			records.put("records", record);
		} catch (Exception e) {

		}
		return records.toString();
	}

	// **************************************************************************//

	public String listState(SessionFactory factory) {

		session = factory.openSession();
		JSONObject records = new JSONObject();

		try {

			Query query = session.createQuery("from State");
			// query.setLong("countryId", countryId);

			List<State> listData = query.list();

			JSONArray record = new JSONArray();

			for (State state : listData) {
				record.put(state.toJSON());
			}
			records.put("records", record);
		} catch (Exception e) {

		}
		return records.toString();
	}

	// **************************************************************************//

	public String listCity(SessionFactory factory) {

		session = factory.openSession();
		JSONObject records = new JSONObject();

		try {
			List<City> listData = session.createQuery("from City").list();

			JSONArray record = new JSONArray();

			for (City city : listData) {
				record.put(city.toJSON());
			}
			records.put("records", record);
		} catch (Exception e) {

		}
		return records.toString();
	}

	// **************************************************************************//

	public String getAll(SessionFactory factory) {

		session = factory.openSession();
		JSONObject records = new JSONObject();

		try {

			List<Country> listData = session.createQuery("from Country").list();

			JSONArray countryRecord = new JSONArray();

			for (Country coun : listData) {
				countryRecord.put(coun.toJSON());
			}

			records.put("countryRecord", countryRecord);

			// -------------------------------------------------------//
			Query query = session.createQuery("from State");
			// query.setLong("countryId", countryId);

			List<State> listData1 = query.list();

			JSONArray stateRecord = new JSONArray();

			for (State state : listData1) {
				stateRecord.put(state.toJSON());
			}
			records.put("stateRecord", stateRecord);

			// -----------------------------------------------------------//
			List<City> listData2 = session.createQuery("from City").list();

			JSONArray cityRecord = new JSONArray();

			for (City city : listData2) {
				cityRecord.put(city.toJSON());
			}
			records.put("cityRecord", cityRecord);
		} catch (Exception e) {

		}

		System.out.println(records.toString());

		return records.toString();
	}

	// ***************************************************************************//
	public String listCountrysState(long countryId, SessionFactory factory) {

		session = factory.openSession();
		JSONObject records = new JSONObject();

		try {
			Query query = session
					.createQuery("from State where countryId=:countryId");
			query.setLong("countryId", countryId);

			List<State> listData = query.list();

			JSONArray record = new JSONArray();

			for (State state : listData) {
				record.put(state.toJSON());
			}
			records.put("records", record);
		} catch (Exception e) {

		}
		return records.toString();
	}

	// ***************************************************************************//
	public String listStatesCity(long stateId, SessionFactory factory) {

		session = factory.openSession();
		JSONObject records = new JSONObject();

		try {
			Query query = session
					.createQuery("from City where stateId=:stateId");
			query.setLong("stateId", stateId);

			List<City> listData = query.list();

			JSONArray record = new JSONArray();

			for (City city : listData) {
				record.put(city.toJSON());
			}
			records.put("records", record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return records.toString();
	}

}
