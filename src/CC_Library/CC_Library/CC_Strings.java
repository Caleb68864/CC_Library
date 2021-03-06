package CC_Library;
import java.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

public class CC_Strings {
	public static String NullToNothing(String str) {
		if (str == null || str == "<null>" || str.isEmpty()|| str == "null" || str == "") {
			str = "";
		}
		return str;
	}

	public static String NullToString(String strInput, String strOutput) {
		String str = null;
		if (strInput == null || strInput == "" || strInput == "<null>" || strInput.isEmpty()) {
			str = strOutput;
		}else{
			str = strInput;
		}
		return str;
	}

	public final static class TextTransfer implements ClipboardOwner {

		// public static void main(String... aArguments) {
		// TextTransfer textTransfer = new TextTransfer();
		//
		// // display what is currently on the clipboard
		// System.out.println("Clipboard contains:"
		// + textTransfer.getClipboardContents());
		//
		// // change the contents and then re-display
		// textTransfer.setClipboardContents("blah, blah, blah");
		// System.out.println("Clipboard contains:"
		// + textTransfer.getClipboardContents());
		// }

		public void lostOwnership(Clipboard aClipboard, Transferable aContents) {
			// do nothing
		}

		/**
		 * Place a String on the clipboard, and make this class the owner of the
		 * Clipboard's contents.
		 */
		public void setClipboardContents(String aString) {
			StringSelection stringSelection = new StringSelection(aString);
			Clipboard clipboard = Toolkit.getDefaultToolkit()
					.getSystemClipboard();
			clipboard.setContents(stringSelection, this);
		}

		/**
		 * Get the String residing on the clipboard.
		 * 
		 * @return any text found on the Clipboard; if none found, return an
		 *         empty String.
		 */
		public String getClipboardContents() {
			String result = "";
			Clipboard clipboard = Toolkit.getDefaultToolkit()
					.getSystemClipboard();
			// odd: the Object param of getContents is not currently used
			Transferable contents = clipboard.getContents(null);
			boolean hasTransferableText = (contents != null)
					&& contents.isDataFlavorSupported(DataFlavor.stringFlavor);
			if (hasTransferableText) {
				try {
					result = (String) contents
							.getTransferData(DataFlavor.stringFlavor);
				} catch (UnsupportedFlavorException ex) {
					// highly unlikely since we are using a standard DataFlavor
					System.out.println(ex);
					ex.printStackTrace();
				} catch (IOException ex) {
					System.out.println(ex);
					ex.printStackTrace();
				}
			}
			return result;
		}
	}
}
