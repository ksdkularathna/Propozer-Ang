package com.bargadss.Propozal.Admin.bean;

import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;

public class Registration {

	private long serialNo;

	// -----------------------------------------//
	private String firstName;
	private String lastName;
	private String email;
	private String password;

	// -----------------------------------------//
	private long country;
	private String forCountry;
	private long state;
	private String forState;
	private long city;
	private String forCity;
	private String gender;
	private String height;
	private String dobYear;
	private long birthYear = 0;
	private String dobMonth;
	private String dobDate;
	private String relegion;
	private String motherTongue;

	// -----------------------------------------//
	private String higestEdu;
	private String careerField;
	private String workingAs;// question_mark
	private long monthlySal;
	private String forSalary;

	// -----------------------------------------//
	private String personType;// myself
	// -----------------------------------------//
	private String familyBackground;
	// -----------------------------------------//
	private String eduAndCareer;
	// -----------------------------------------//
	private String lookingFor;
	// -----------------------------------------//
	private String facebookUrl;
	private String linkdinUrl;
	private String nicNumber;
	private long mobileNumber;
	private String forMobile;
	private String status;
	private String adminStatus;
	// -----------------------------------------//

	private String FBstatus, Lstatus, NICstatus;

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}
	
	
	public String getFBstatus() {
		return FBstatus;
	}

	public void setFBstatus(String fBstatus) {
		FBstatus = fBstatus;
	}

	public String getLstatus() {
		return Lstatus;
	}

	public void setLstatus(String lstatus) {
		Lstatus = lstatus;
	}

	public String getNICstatus() {
		return NICstatus;
	}

	public void setNICstatus(String nICstatus) {
		NICstatus = nICstatus;
	}

	public String getForMobile() {
		return forMobile;
	}

	public void setForMobile(String forMobile) {
		this.forMobile = forMobile;
	}

	public Registration() {
	}

	public long getSerialNo() {
		return serialNo;
	}

	public long getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(long birthYear) {
		this.birthYear = birthYear;
	}

	public void setSerialNo(long serialNo) {
		this.serialNo = serialNo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getCountry() {
		return country;
	}

	public void setCountry(long country) {
		this.country = country;
	}

	public long getCity() {
		return city;
	}

	public void setCity(long city) {
		this.city = city;
	}

	public long getState() {
		return state;
	}

	public void setState(long state) {
		this.state = state;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getDobYear() {
		return dobYear;
	}

	public void setDobYear(String dobYear) {
		this.dobYear = dobYear;
	}

	public String getDobMonth() {
		return dobMonth;
	}

	public void setDobMonth(String dobMonth) {
		this.dobMonth = dobMonth;
	}

	public String getDobDate() {
		return dobDate;
	}

	public void setDobDate(String dobDate) {
		this.dobDate = dobDate;
	}

	public String getRelegion() {
		return relegion;
	}

	public void setRelegion(String relegion) {
		this.relegion = relegion;
	}

	public String getMotherTongue() {
		return motherTongue;
	}

	public void setMotherTongue(String motherTongue) {
		this.motherTongue = motherTongue;
	}

	public String getHigestEdu() {
		return higestEdu;
	}

	public void setHigestEdu(String higestEdu) {
		this.higestEdu = higestEdu;
	}

	public String getCareerField() {
		return careerField;
	}

	public void setCareerField(String careerField) {
		this.careerField = careerField;
	}

	public String getWorkingAs() {
		return workingAs;
	}

	public void setWorkingAs(String workingAs) {
		this.workingAs = workingAs;
	}

	public long getMonthlySal() {
		return monthlySal;
	}

	public void setMonthlySal(long monthlySal) {
		this.monthlySal = monthlySal;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getFamilyBackground() {
		return familyBackground;
	}

	public void setFamilyBackground(String familyBackground) {
		this.familyBackground = familyBackground;
	}

	public String getEduAndCareer() {
		return eduAndCareer;
	}

	public void setEduAndCareer(String eduAndCareer) {
		this.eduAndCareer = eduAndCareer;
	}

	public String getLookingFor() {
		return lookingFor;
	}

	public void setLookingFor(String lookingFor) {
		this.lookingFor = lookingFor;
	}

	public String getFacebookUrl() {
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public String getLinkdinUrl() {
		return linkdinUrl;
	}

	public void setLinkdinUrl(String linkdinUrl) {
		this.linkdinUrl = linkdinUrl;
	}

	public String getNicNumber() {
		return nicNumber;
	}

	public void setNicNumber(String nicNumber) {
		this.nicNumber = nicNumber;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	// ***********************************************************************************************//

	public String getForCountry() {
		return forCountry;
	}

	public void setForCountry(String forCountry) {
		this.forCountry = forCountry;
	}

	public String getForState() {
		return forState;
	}

	public void setForState(String forState) {
		this.forState = forState;
	}

	public String getForCity() {
		return forCity;
	}

	public void setForCity(String forCity) {
		this.forCity = forCity;
	}

	public String getForSalary() {
		return forSalary;
	}

	public void setForSalary(String forSalary) {
		this.forSalary = forSalary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// **********************************************************************************************//
	public JSONObject toEditJSON() throws org.json.JSONException,
			ParseException {
		JSONObject jo = new JSONObject();

		jo.put("serialNo", serialNo);
		jo.put("firstName", firstName);
		jo.put("lastName", lastName);
		jo.put("email", email);
		jo.put("password", password);
		// jo.put("country", country);
		// jo.put("city", city);
		// jo.put("state",state);
		jo.put("gender", gender);
		jo.put("height", height);
		jo.put("dobYear", dobYear);
		jo.put("dobMonth", dobMonth);
		jo.put("dobDate", dobDate);
		jo.put("relegion", relegion);
		jo.put("motherTongue", motherTongue);
		jo.put("higestEdu", higestEdu);
		jo.put("careerField", careerField);
		jo.put("workingAs", workingAs);

		if (monthlySal == 1) {
			jo.put("monthlySal", "0-10000 LKR");
		} else if (monthlySal == 2) {
			jo.put("monthlySal", "10001-50000 LKR");
		} else if (monthlySal == 3) {
			jo.put("monthlySal", "50001-100000 LKR");
		} else if (monthlySal == 4) {
			jo.put("monthlySal", "100001-300000 LKR");
		} else if (monthlySal == 5) {
			jo.put("monthlySal", "Above 300001 LKR");
		}

		// jo.put("monthlySal", monthlySal);
		jo.put("personType", personType);
		jo.put("familyBackground", familyBackground);
		jo.put("eduAndCareer", eduAndCareer);
		jo.put("lookingFor", lookingFor);
		jo.put("facebookUrl", facebookUrl);
		jo.put("linkdinUrl", linkdinUrl);
		jo.put("nicNumber", nicNumber);
		jo.put("mobileNumber", mobileNumber);

		jo.put("forCountry", forCountry);
		jo.put("forState", forState);
		jo.put("forCity", forCity);
		jo.put("forSalary", forSalary);
		jo.put("forMobile", forMobile);
		jo.put("status", status);

		String notVerified = "notDone";

		if (FBstatus == null) {
			jo.put("FBstatus", notVerified);
		} else {
			jo.put("FBstatus", FBstatus);
		}

		if (Lstatus == null) {
			jo.put("Lstatus", notVerified);
		} else {
			jo.put("Lstatus", Lstatus);
		}

		if (NICstatus == null) {
			jo.put("NICstatus", notVerified);
		} else {
			jo.put("NICstatus", NICstatus);
		}
		System.out.println(adminStatus);
		
		jo.put("adminStatus", adminStatus);
		//
		// jo.put("FBstatus", FBstatus);
		// jo.put("Lstatus", Lstatus);
		// jo.put("NICstatus", NICstatus);

		return jo;
	}

	// **************************************************************************************************//
	public JSONObject toJSON() throws JSONException {

		JSONObject jo = new JSONObject();

		String pending = "PenDing0o";
		String blank="";

		jo.put("serialNo", serialNo);

		if (!firstName.equalsIgnoreCase(pending)) {
			jo.put("firstName", firstName);
		}else{
			jo.put("firstName", blank);
		}

		if (!lastName.equalsIgnoreCase(pending)) {
			jo.put("lastName", lastName);
		}else{
			jo.put("lastName", blank);
		}

		if (!email.equalsIgnoreCase(pending)) {
			jo.put("email", email);
		}else{
			jo.put("email", blank);
		}

		if (!password.equalsIgnoreCase(pending)) {
			jo.put("password", password);
		}else{
			jo.put("password", blank);
		}
		// jo.put("country", country);
		// jo.put("city", city);
		// jo.put("state",state);

		if (!gender.equalsIgnoreCase(pending)) {
			jo.put("gender", gender);
		}else{
			jo.put("gender", blank);
		}

		if (!height.equalsIgnoreCase(pending)) {
			jo.put("height", height);
		}else{
			jo.put("height", blank);
		}

		if (!dobYear.equalsIgnoreCase(pending)) {
			jo.put("dobYear", dobYear);
		}else{
			jo.put("dobYear", blank);
		}

		if (!dobMonth.equalsIgnoreCase(pending)) {
			jo.put("dobMonth", dobMonth);
		}else{
			jo.put("dobMonth", blank);
		}

		if (!dobDate.equalsIgnoreCase(pending)) {
			jo.put("dobDate", dobDate);
		}else{
			jo.put("dobDate", blank);
		}

		if (!relegion.equalsIgnoreCase(pending)) {
			jo.put("relegion", relegion);
		}else{
			jo.put("relegion", blank);
		}

		if (!motherTongue.equalsIgnoreCase(pending)) {
			jo.put("motherTongue", motherTongue);
		}else{
			jo.put("motherTongue", blank);
		}

		if (!higestEdu.equalsIgnoreCase(pending)) {
			jo.put("higestEdu", higestEdu);
		}else{
			jo.put("higestEdu", blank);
		}

		if (!careerField.equalsIgnoreCase(pending)) {
			jo.put("careerField", careerField);
		}else{
			jo.put("careerField", blank);
		}

		if (!workingAs.equalsIgnoreCase(pending)) {
			jo.put("workingAs", workingAs);
		}else{
			jo.put("workingAs", blank);
		}

		if (!forSalary.equalsIgnoreCase(pending)) {

			System.out.println("monthlySal: " + monthlySal);

			if (monthlySal == 1) {
				jo.put("monthlySal", "0-10000 LKR");
			} else if (monthlySal == 2) {
				jo.put("monthlySal", "10001-50000 LKR");
			} else if (monthlySal == 3) {
				jo.put("monthlySal", "50001-100000 LKR");
			} else if (monthlySal == 4) {
				jo.put("monthlySal", "100001-300000 LKR");
			} else if (monthlySal == 5) {
				jo.put("monthlySal", "Above 300001 LKR");
			}
			// jo.put("monthlySal", monthlySal);
		}else{
			jo.put("monthlySal", blank);
		}

		if (!personType.equalsIgnoreCase(pending)) {
			jo.put("personType", personType);
		}else{
			jo.put("personType", blank);
		}

		if (!familyBackground.equalsIgnoreCase(pending)) {
			jo.put("familyBackground", familyBackground);
		}else{
			jo.put("familyBackground", blank);
		}

		if (!eduAndCareer.equalsIgnoreCase(pending)) {
			jo.put("eduAndCareer", eduAndCareer);
		}else{
			jo.put("eduAndCareer", blank);
		}

		if (!lookingFor.equalsIgnoreCase(pending)) {
			jo.put("lookingFor", lookingFor);
		}else{
			jo.put("lookingFor", blank);
		}

		if (!facebookUrl.equalsIgnoreCase(pending)) {
			jo.put("facebookUrl", facebookUrl);
		}else{
			jo.put("facebookUrl", blank);
		}

		if (!linkdinUrl.equalsIgnoreCase(pending)) {
			jo.put("linkdinUrl", linkdinUrl);
		}else{
			jo.put("linkdinUrl", blank);
		}

		if (!nicNumber.equalsIgnoreCase(pending)) {
			jo.put("nicNumber", nicNumber);
		}else{
			jo.put("nicNumber", blank);
		}

		if (!forMobile.equalsIgnoreCase(pending)) {
			jo.put("mobileNumber", mobileNumber);
		}else{
			jo.put("mobileNumber", blank);
		}

		if (!forCountry.equalsIgnoreCase(pending)) {
			jo.put("forCountry", forCountry);
		}

		if (!forState.equalsIgnoreCase(pending)) {
			jo.put("forState", forState);
		}

		if (!forCity.equalsIgnoreCase(pending)) {
			jo.put("forCity", forCity);
		}

		if (!forSalary.equalsIgnoreCase(pending)) {
			jo.put("forSalary", forSalary);
		}

		jo.put("status", status);
		
		String notVerified = "notDone";

		if (FBstatus == null) {
			jo.put("FBstatus", notVerified);
		} else {
			jo.put("FBstatus", FBstatus);
		}

		if (Lstatus == null) {
			jo.put("Lstatus", notVerified);
		} else {
			jo.put("Lstatus", Lstatus);
		}

		if (NICstatus == null) {
			jo.put("NICstatus", notVerified);
		} else {
			jo.put("NICstatus", NICstatus);
		}
		
		System.out.println(adminStatus);
		
		jo.put("adminStatus", adminStatus);
		return jo;
	}

	// **********************************************************************************************//

	public Registration(org.json.simple.JSONObject inputJsonObj)
			throws org.json.JSONException, ParseException {

		String pending = "PenDing0o";
		String done = "D0ne";

		if (inputJsonObj.containsKey("serialNo"))
			serialNo = (long) inputJsonObj.get("serialNo");
		else
			serialNo = 0;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("firstName")) {

			try {
				firstName = (String) inputJsonObj.get("firstName");

				if (firstName == null) {
					firstName = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				firstName = pending;
			}

		} else
			firstName = pending;
		// ----------------------------------------------------------------------------------//

		if (inputJsonObj.containsKey("lastName")) {

			try {
				lastName = (String) inputJsonObj.get("lastName");

				if (lastName == null) {
					lastName = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				lastName = pending;
			}

		} else
			lastName = pending;
		// ----------------------------------------------------------------------------------//

		if (inputJsonObj.containsKey("email")) {

			try {
				email = (String) inputJsonObj.get("email");

				if (email == null) {
					email = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				email = pending;
			}

		} else
			lastName = pending;
		// ----------------------------------------------------------------------------------//

		if (inputJsonObj.containsKey("password")) {

			try {
				password = (String) inputJsonObj.get("password");

				if (password == null) {
					password = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				password = pending;
			}

		} else
			password = pending;
		// ----------------------------------------------------------------------------------//

		if (inputJsonObj.containsKey("country")) {

			try {
				country = (long) inputJsonObj.get("country");

				if (country != 0) {
					forCountry = done;
				} else {
					forCountry = pending;
				}
			} catch (Exception e) {

				try {
					country = Long
							.valueOf((String) inputJsonObj.get("country"));
					if (country != 0) {
						forCountry = done;
					} else {
						forCountry = pending;
					}

				} catch (Exception e2) {
					country = 0;
					forCountry = pending;
				}
			}

		} else {
			country = 0;
			forCountry = pending;
		}

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("city")) {

			try {
				city = (long) inputJsonObj.get("city");

				if (city != 0) {
					forCity = done;
				} else {
					forCity = pending;
				}
			} catch (Exception e) {
				try {
					city = Long.valueOf((String) inputJsonObj.get("city"));

					if (city != 0) {
						forCity = done;
					} else {
						forCity = pending;
					}
				} catch (Exception e1) {
					city = 0;
					forCity = pending;
				}
			}

		} else {
			city = 0;
			forCity = pending;
		}

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("state")) {

			try {
				state = (long) inputJsonObj.get("state");

				if (state != 0) {
					forState = done;
				} else {
					forState = pending;
				}
			} catch (Exception e) {
				try {
					state = Long.valueOf((String) inputJsonObj.get("state"));

					if (state != 0) {
						forState = done;
					} else {
						forState = pending;
					}
				} catch (Exception e1) {
					state = 0;
					forState = pending;
				}
			}

		} else {
			state = 0;
			forState = pending;
		}

		// ----------------------------------------------------------------------------------//

		if (inputJsonObj.containsKey("gender")) {

			try {
				gender = (String) inputJsonObj.get("gender");

				if (gender == null) {
					gender = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				gender = pending;
			}

		} else
			gender = pending;

		// ----------------------------------------------------------------------------------//

		if (inputJsonObj.containsKey("height")) {

			try {
				height = (String) inputJsonObj.get("height");

				if (height == null) {
					height = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				height = pending;
			}

		} else
			height = pending;

		// ----------------------------------------------------------------------------------//

		if (inputJsonObj.containsKey("dobYear")) {

			try {
				dobYear = (String) inputJsonObj.get("dobYear");

				if (dobYear == null) {
					dobYear = pending;
					birthYear = 0;
				} else {
					birthYear = Long.valueOf(dobYear);
				}

			} catch (Exception e) {
				e.printStackTrace();
				dobYear = pending;
				birthYear = 0;
			}

		} else {
			dobYear = pending;
			birthYear = 0;
		}

		// ----------------------------------------------------------------------------------//

		if (inputJsonObj.containsKey("dobMonth")) {

			try {
				dobMonth = (String) inputJsonObj.get("dobMonth");

				if (dobMonth == null) {
					dobMonth = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				dobMonth = pending;
			}

		} else
			dobMonth = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("dobDate")) {

			try {
				dobDate = (String) inputJsonObj.get("dobDate");

				if (dobDate == null) {
					dobDate = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				dobDate = pending;
			}

		} else
			dobDate = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("relegion")) {

			try {
				relegion = (String) inputJsonObj.get("relegion");

				if (relegion == null) {
					relegion = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				relegion = pending;
			}

		} else
			relegion = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("lookingFor")) {

			try {
				lookingFor = (String) inputJsonObj.get("lookingFor");

				if (lookingFor == null) {
					lookingFor = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				lookingFor = pending;
			}

		} else
			lookingFor = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("facebookUrl")) {

			try {
				facebookUrl = (String) inputJsonObj.get("facebookUrl");

				if (facebookUrl == null) {
					facebookUrl = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				facebookUrl = pending;
			}

		} else
			facebookUrl = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("linkdinUrl")) {

			try {
				linkdinUrl = (String) inputJsonObj.get("linkdinUrl");

				if (linkdinUrl == null) {
					linkdinUrl = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				linkdinUrl = pending;
			}

		} else
			linkdinUrl = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("nicNumber")) {

			try {
				nicNumber = (String) inputJsonObj.get("nicNumber");

				if (nicNumber == null) {
					nicNumber = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				nicNumber = pending;
			}

		} else
			nicNumber = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("mobileNumber")) {

			try {
				mobileNumber = (long) inputJsonObj.get("mobileNumber");
				System.out.println("********************************"
						+ mobileNumber);

				if (mobileNumber != 0) {
					forMobile = done;
				} else {
					forMobile = pending;
				}
			} catch (Exception e) {
				try {
					mobileNumber = Long.valueOf((String) inputJsonObj
							.get("mobileNumber"));

					System.out.println("********************************"
							+ mobileNumber);

					if (mobileNumber != 0) {
						forMobile = done;
					} else {
						forMobile = pending;
					}
				} catch (Exception e1) {
					mobileNumber = 0;
					forMobile = pending;
				}
			}

		} else {
			mobileNumber = 0;
			forMobile = pending;
		}

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("monthlySal")) {

			try {
				monthlySal = (long) inputJsonObj.get("monthlySal");

				if (monthlySal != 0) {
					forSalary = done;
				} else {
					forSalary = pending;
				}
			} catch (Exception e) {
				try {
					monthlySal = Long.valueOf((String) inputJsonObj
							.get("monthlySal"));

					if (monthlySal != 0) {
						forSalary = done;
					} else {
						forSalary = pending;
					}
				} catch (Exception e1) {
					monthlySal = 0;
					forSalary = pending;
				}
			}

		} else {
			monthlySal = 0;
			forSalary = pending;
		}

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("personType")) {

			try {
				personType = (String) inputJsonObj.get("personType");

				if (personType == null) {
					personType = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				personType = pending;
			}

		} else
			personType = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("familyBackground")) {

			try {
				familyBackground = (String) inputJsonObj
						.get("familyBackground");

				if (familyBackground == null) {
					familyBackground = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				familyBackground = pending;
			}

		} else
			familyBackground = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("eduAndCareer")) {

			try {
				eduAndCareer = (String) inputJsonObj.get("eduAndCareer");

				if (eduAndCareer == null) {
					eduAndCareer = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				eduAndCareer = pending;
			}

		} else
			eduAndCareer = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("motherTongue")) {

			try {
				motherTongue = (String) inputJsonObj.get("motherTongue");

				if (motherTongue == null) {
					motherTongue = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				motherTongue = pending;
			}

		} else
			motherTongue = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("higestEdu")) {

			try {
				higestEdu = (String) inputJsonObj.get("higestEdu");

				if (higestEdu == null) {
					higestEdu = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				higestEdu = pending;
			}

		} else
			higestEdu = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("careerField")) {

			try {
				careerField = (String) inputJsonObj.get("careerField");

				if (careerField == null) {
					careerField = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				careerField = pending;
			}

		} else
			careerField = pending;

		// ----------------------------------------------------------------------------------//
		if (inputJsonObj.containsKey("workingAs")) {

			try {
				workingAs = (String) inputJsonObj.get("workingAs");

				if (workingAs == null) {
					workingAs = pending;
				}

			} catch (Exception e) {
				e.printStackTrace();
				workingAs = pending;
			}
		} else
			workingAs = pending;

		if (inputJsonObj.containsKey("status")) {

			try {
				status = (String) inputJsonObj.get("status");

				if (status == null) {
					status = "pending";
				}

			} catch (Exception e) {
				e.printStackTrace();
				status = "pending";
			}
		} else
			status = "pending";
		if (inputJsonObj.containsKey("adminStatus")) {

			try {
				adminStatus = (String) inputJsonObj.get("adminStatus");

				if (adminStatus == null) {
					adminStatus = "pending";
				}

			} catch (Exception e) {
				e.printStackTrace();
				adminStatus = "pending";
			}
		} else
			adminStatus = "pending";
	}

	// **********************************************************************************************//
	@Override
	public String toString() {
		return "Registration [serialNo=" + serialNo + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", country=" + country
				+ ", forCountry=" + forCountry + ", state=" + state
				+ ", forState=" + forState + ", city=" + city + ", forCity="
				+ forCity + ", gender=" + gender + ", height=" + height
				+ ", dobYear=" + dobYear + ", birthYear=" + birthYear
				+ ", dobMonth=" + dobMonth + ", dobDate=" + dobDate
				+ ", relegion=" + relegion + ", motherTongue=" + motherTongue
				+ ", higestEdu=" + higestEdu + ", careerField=" + careerField
				+ ", workingAs=" + workingAs + ", monthlySal=" + monthlySal
				+ ", forSalary=" + forSalary + ", personType=" + personType
				+ ", familyBackground=" + familyBackground + ", eduAndCareer="
				+ eduAndCareer + ", lookingFor=" + lookingFor
				+ ", facebookUrl=" + facebookUrl + ", linkdinUrl=" + linkdinUrl
				+ ", nicNumber=" + nicNumber + ", mobileNumber=" + mobileNumber
				+ ", forMobile=" + forMobile + ", status=" + status + ",adminStatus=" + adminStatus + "]";
	}

	// **********************************************************************************************//

}
