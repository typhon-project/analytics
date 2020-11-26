package ac.york.typhon.analytics.commons.datatypes.tests;

import java.util.ArrayList;
import java.util.Date;

import ac.york.typhon.analytics.commons.datatypes.*;
import ac.york.typhon.analytics.commons.datatypes.events.Entity;
import org.apache.flink.api.java.functions.KeySelector;

public class Datatypes extends Entity{
	private String s;
	
	public String getS() {
		return this.s;
	}
	
	public void setS(String s) {
		this.s = s;
	}
	private Integer i;
	
	public Integer getI() {
		return this.i;
	}
	
	public void setI(Integer i) {
		this.i = i;
	}
	private Boolean b;
	
	public Boolean getB() {
		return this.b;
	}
	
	public void setB(Boolean b) {
		this.b = b;
	}
	private Integer bi;
	
	public Integer getBi() {
		return this.bi;
	}
	
	public void setBi(Integer bi) {
		this.bi = bi;
	}
	private Double f;
	
	public Double getF() {
		return this.f;
	}
	
	public void setF(Double f) {
		this.f = f;
	}
	private String d;
	
	public String getD() {
		return this.d;
	}
	
	public void setD(String d) {
		this.d = d;
	}
	private String dt;
	
	public String getDt() {
		return this.dt;
	}
	
	public void setDt(String dt) {
		this.dt = dt;
	}
	private Point p;
	
	public Point getP() {
		return this.p;
	}
	
	public void setP(Point p) {
		this.p = p;
	}
	private Polygon pol;
	
	public Polygon getPol() {
		return this.pol;
	}
	
	public void setPol(Polygon pol) {
		this.pol = pol;
	}
	
	private Refer ref;
	
	public Refer getRef() {
		return this.ref;
	}
	
	public void setRef(Refer ref) {
		this.ref = ref;
	}
	
	private ArrayList<Refer> mref;
	
	public ArrayList<Refer> getMref() {
		return this.mref;
	}
	
	public void setMref(ArrayList<Refer> mref) {
		this.mref = mref;
	}
	
	private CRefer cref;
	
	public CRefer getCref() {
		return this.cref;
	}
	
	public void setCref(CRefer cref) {
		this.cref = cref;
	}

	public String toString() { 
		String result = "";
	
		result = "Datatypes(" + " s: " + s + " i: " + i + " b: " + b + " bi: " + bi + " f: " + f + " d: " + d + " dt: " + dt + " p: " + p + " pol: " + pol + " ref: " + ref + " mref: " + mref + " cref: " + cref + " previousvalue: " + getPreviousValue() + " uuid: " + getUUID() + ")";
		return result;
	}
	
	public static KeySelector<Datatypes, String> getKeySelector(String keyName) {
		
		return new DatatypesKeySelector(keyName);
	}
}

