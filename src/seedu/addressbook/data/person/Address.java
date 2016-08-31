package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book. Guarantees: immutable; is
 * valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

	public static final String EXAMPLE = "123, some street,  #0-00, 999999";
	public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in the format a/BLOCK, STREET, UNIT, POSTAL_CODE";
	public static final String ADDRESS_VALIDATION_REGEX = ".+,.+,.+,.+";
	public static final int ADDRESS_INDEX_BLOCK = 0;
	public static final int ADDRESS_INDEX_STREET = 1;
	public static final int ADDRESS_INDEX_UNIT = 2;
	public static final int ADDRESS_INDEX_POSTALCODE = 3;

	private Block block;
	private Street street;
	private Unit unit;
	private PostalCode postalCode;
	private boolean isPrivate;

	/**
	 * Validates given address.
	 *
	 * @throws IllegalValueException
	 *             if given address string is invalid.
	 */

	public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if (!isValidAddress(address)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] addressParts = chopByComma(address);
        this.block = new Block(addressParts[ADDRESS_INDEX_BLOCK].trim());
        this.street = new Street(addressParts[ADDRESS_INDEX_STREET].trim());
        this.unit = new Unit(addressParts[ADDRESS_INDEX_UNIT].trim());
        this.postalCode = new PostalCode(addressParts[ADDRESS_INDEX_POSTALCODE].trim());
    }
        

	/**
	 * Returns true if a given string is a valid person email.
	 */
	public static boolean isValidAddress(String test) {
		return test.matches(ADDRESS_VALIDATION_REGEX) && chopByComma(test).length == 4;
	}

	public static String[] chopByComma(String address) {
		String[] addressParts = address.split(",");
		return addressParts;
	}

	@Override
	public String toString() {
		return this.block.getBlock() + ", " + this.street.getStreet() + ", " + this.unit.getUnit() + ", "
				+ this.postalCode.getPostalCode();
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof Address // instanceof handles nulls
						&& this.toString().equals(((Address) other).toString())); // state
																					// check
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}

	public boolean isPrivate() {
		return isPrivate;
	}
}