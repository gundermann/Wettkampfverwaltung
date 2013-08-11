package definition;

public enum Score {
	NONE, WAZARI, AWASETEIPPON, IPPON;
	
	public int toNumber(){
		switch (this) {
		case WAZARI:
			return 1;
		case AWASETEIPPON:
			return 2;
		case IPPON:
			return 2;
		default:
			return 0;
		}
	}
}
