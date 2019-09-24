package reim.model;

import java.io.Serializable;
import java.util.Arrays;

public class Receipt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
reim_id int not null,
image bytea,
image_name varchar(50)
	 */
	
	private Reimbursement reimbursement;
	private Byte[] image;
	private String imageName;
	//
	public Receipt() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Receipt(Reimbursement reimbursement, Byte[] image, String imageName) {
		super();
		this.reimbursement = reimbursement;
		this.image = image;
		this.imageName = imageName;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result + ((reimbursement == null) ? 0 : reimbursement.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Receipt other = (Receipt) obj;
		if (!Arrays.equals(image, other.image))
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		if (reimbursement == null) {
			if (other.reimbursement != null)
				return false;
		} else if (!reimbursement.equals(other.reimbursement))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Receipt [reimbursement=" + reimbursement + ", image=" + Arrays.toString(image) + ", imageName="
				+ imageName + "]";
	}
	public Reimbursement getReimbursement() {
		return reimbursement;
	}
	public void setReimbursement(Reimbursement reimbursement) {
		this.reimbursement = reimbursement;
	}
	public Byte[] getImage() {
		return image;
	}
	public void setImage(Byte[] image) {
		this.image = image;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	

}
