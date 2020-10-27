package g4dhl;

public interface IAging {
	int getAverageRetirementAge();

	boolean setAverageRetirementAge(int averageRetirementAge);

	int getMaximumAge();

	boolean setMaximumAge(int maximumAge);

	int getAgingId();

	void setAgingId(int agingId);

	boolean isPlayerRetires(int playerAgeYear);
}
