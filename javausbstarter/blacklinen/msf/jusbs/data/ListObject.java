package blacklinen.msf.jusbs.data;
/**
 * Represent's a ListObject of the JavaUSBStarter.
 * 
 * @author Mark
 * @version 1.0
 */
public interface ListObject
{
	/**
	 * Return's the name of the represented ListObject.
	 * 
	 * @return The name of the ListObject.
	 */
	public String getName();
	/**
	 * Set's the name of the represented ListObject.
	 * 
	 * @param newName The new Name.
	 */
	public void setName(String newName);
	/**
	 * Return's the tag of the represented ListObject.
	 * 
	 * @return The tag of the ListObject.
	 */
	public String getTag();
	/**
	 * Set's the tag of the represented ListObject.
	 * 
	 * @param newTag The new Tag.
	 */
	public void setTag(String newTag);
	/**
	 * Return's the path of the represented ListObject.
	 * 
	 * @return The path.
	 */
	public String getPath();
	/**
	 * Set's the path of the represented ListObject.
	 * 
	 * @param newPath The new path.
	 */
	public void setPath(String newPath);
}
