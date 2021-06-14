
public class Tiket {
	String nama, nohp, tujuan, tgl, jemput, ongkos;

	public Tiket(String nama, String nohp, String tujuan, String ongkos, String tgl, String jemput) {
		super();
		this.nama = nama;
		this.nohp = nohp;
		this.tujuan = tujuan;
		this.ongkos = ongkos;
		this.tgl = tgl;
		this.jemput = jemput;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNohp() {
		return nohp;
	}

	public void setNohp(String nohp) {
		this.nohp = nohp;
	}

	public String getOngkos() {
		return ongkos;
	}

	public void setOngkos(String ongkos) {
		this.ongkos = ongkos;
	}

	public String getTgl() {
		return tgl;
	}

	public void setTgl(String tgl) {
		this.tgl = tgl;
	}

	public String getJemput() {
		return jemput;
	}

	public void setJemput(String jemput) {
		this.jemput = jemput;
	}
	
}
