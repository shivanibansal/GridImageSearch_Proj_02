package com.yahoo.bshivani.gridimagesearch_proj_02.models;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class GoogleSearchAPI {
	// Arguments
	public String mOptImageSize;
	public String mOptImageColor;
	public String mOptImageType;
	public String mOptSitSearch;
	public String mOptFileType;

	// Constants - Base URL
	public final String BASE_URL = "https://ajax.googleapis.com/ajax/services/search/images";

	// Constants - Mandatory Arguments String
	public final String KEY_QUERY = "q="; 				// query, or search expression
	public final String KEY_VAL_VERSION = "v=1.0"; 		// only valid value at this point in time is 1.0
	public final String KEY_RESULT_PER_PAGE = "rsz="; 	// 1Ð8 indicating the number of results to return per page.
	public final String KEY_OFFSET = "start="; 			// start index of the first
														// search result

	// Constants - Optional Arguments String
	public final String KEY_IMAGE_SIZE = "imgsz="; 			// Image Size
	public final String KEY_IMAGE_COLOR = "imgcolor="; 		// Image Color
	public final String KEY_IAMGE_TYPE = "imgtype="; 		// Image Type
	public final String KEY_SITE_SEARCH = "as_sitesearch="; // Restrict Image
															// search within the
															// specified domain
	public final String KEY_FILE_TYPE = "as_filetype="; 	// File Type

	// Constants - Value - Image Size - small, medium, large, extra-large
	public final static	String STR_VAL_IMAGE_SIZE_SMALL = "icon";
//	public final static	String STR_VAL_IMAGE_SIZE_MEDIUM = "small|medium|large|xlarge"; // TODO Need to encode | character
	public static String OR_SEP = ""; //= URLEncoder.encode("|", "UTF-8");
	public static String STR_VAL_IMAGE_SIZE_MEDIUM = ""; //"small"+ OR_SEP+"medium"+ OR_SEP+"large"+ OR_SEP+"xlarge"; 
	
	public final static	String STR_VAL_IMAGE_SIZE_LARGE = "xxlarge";
	public final static	String STR_VAL_IMAGE_SIZE_EXTRA_LARGE = "huge";
	// Argument Value Name
	public final static	String STR_ARR_VAL_IMAGE_SIZE_SMALL = "small";
	public final static	String STR_ARR_VAL_IMAGE_SIZE_MEDIUM = "medium";
	public final static	String STR_ARR_VAL_IMAGE_SIZE_LARGE = "large";
	public final static	String STR_ARR_VAL_IMAGE_SIZE_EXTRA_LARGE = "extra large";
	// Index - Should be consecutive and in range 1 to 4
	public final int indxImageSizeMin = 1;
	public final int indxImageSizeSmall = 1;
	public final int indxImageSizeMedium = 2;
	public final int indxImageSizeLarge = 3;
	public final int indxImageSizeExtraLarge = 4;
	public final int indxImageSizeMax = 4;

	// Constants - Value - Image Color - black, blue, brown, gray, green,
	// orange, pink, purple, red, teal, white, yellow
	public final static	String STR_VAL_IMAGE_COLOR_BLACK = "black";
	public final static	String STR_VAL_IMAGE_COLOR_BLUE = "blue";
	public final static	String STR_VAL_IMAGE_COLOR_BROWN = "brown";
	public final static	String STR_VAL_IMAGE_COLOR_GRAY = "gray";
	public final static	String STR_VAL_IMAGE_COLOR_GREEN = "green";
	public final static String STR_VAL_IMAGE_COLOR_ORANGE = "orange";
	public final static String STR_VAL_IMAGE_COLOR_PINK = "pink";
	public final static String STR_VAL_IMAGE_COLOR_PURPLE = "purple";
	public final static String STR_VAL_IMAGE_COLOR_RED = "red";
	public final static String STR_VAL_IMAGE_COLOR_TEAL = "teal";
	public final static String STR_VAL_IMAGE_COLOR_WHITE = "white";
	public final static String STR_VAL_IMAGE_COLOR_YELLOW = "yellow";
	// Index - Should be consecutive and in range 1 to 12
	public final int indxImageColorMin = 1;
	public final int indxImageColorBlack = 1;
	public final int indxImageColorBlue = 2;
	public final int indxImageColorBrown = 3;
	public final int indxImageColorGray = 4;
	public final int indxImageColorGreen = 5;
	public final int indxImageColorOrange = 6;
	public final int indxImageColorPink = 7;
	public final int indxImageColorPurple = 8;
	public final int indxImageColorRed = 9;
	public final int indxImageColorTeal = 10;
	public final int indxImageColorWhite = 11;
	public final int indxImageColorYellow = 12;
	public final int indxImageColorMax = 12;

	// Constants - Value - Image Type
	public final static String STR_VAL_IMAGE_TYPE_FACE = "face";
	public final static String STR_VAL_IMAGE_TYPE_PHOTO = "photo";
	public final static String STR_VAL_IMAGE_TYPE_CLIPART = "clipart";
	public final static String STR_VAL_IMAGE_TYPE_LINEART = "lineart";
	// Index - Should be consecutive and in range 1 to 4
	public final int indxImageTypeMin = 1;
	public final int indxImageTypeFace = 1;
	public final int indxImageTypePhoto = 2;
	public final int indxImageTypeClipArt = 3;
	public final int indxImageTypeLineArt = 4;
	public final int indxImageTypeMax = 4;

	// Constants - Value - File Type - jpg, png, gif, bmp
	public final static String STR_VAL_FILE_TYPE_JPG = "jpg";
	public final static String STR_VAL_FILE_TYPE_PNG = "png";
	public final static String STR_VAL_FILE_TYPE_GIF = "gif";
	public final static String STR_VAL_FILE_TYPE_BMP = "bmp";
	// Index - Should be consecutive and in range 1 to 4
	public final int indxFileTypeMin = 1;
	public final int indxFileTypeJpg = 1;
	public final int indxFileTypePng = 2;
	public final int indxFileTypeGif = 3;
	public final int indxFileTypeBmp = 4;
	public final int indxFileTypeMax = 4;

	// Constructor
	public GoogleSearchAPI() {
		mOptImageSize = "";
		mOptImageColor = "";
		mOptImageType = "";
		mOptSitSearch = "";
		mOptFileType = "";
		
		try {
			OR_SEP = URLEncoder.encode("|", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		STR_VAL_IMAGE_SIZE_MEDIUM = "small"+ OR_SEP+"medium"+ OR_SEP+"large"+ OR_SEP+"xlarge"; 
	}
	
	public void setFromSearchSettingsObj (SearchOptionSettings settingObj)
	{
		setOptParamStrings(settingObj.argImageSize, settingObj.argImageColor, settingObj.argImageType, settingObj.argSiteSearch, settingObj.argFileType);
	}
	
	public void setOptParamStrings(String strImageSize, String strImageColor, String strImageType, String sSiteSerach, String strFileType) {
		// Image Size
		{
			if (strImageSize.equalsIgnoreCase(STR_ARR_VAL_IMAGE_SIZE_SMALL) == true) {
				mOptImageSize = STR_VAL_IMAGE_SIZE_SMALL;
			} else if (strImageSize.equalsIgnoreCase(STR_ARR_VAL_IMAGE_SIZE_MEDIUM) == true) {
				mOptImageSize = STR_VAL_IMAGE_SIZE_MEDIUM;
			} else if (strImageSize.equalsIgnoreCase(STR_ARR_VAL_IMAGE_SIZE_LARGE) == true) {
				mOptImageSize = STR_VAL_IMAGE_SIZE_LARGE;
			} else if (strImageSize.equalsIgnoreCase(STR_ARR_VAL_IMAGE_SIZE_EXTRA_LARGE) == true) {
				mOptImageSize = STR_VAL_IMAGE_SIZE_EXTRA_LARGE;
			}
		}

		// Image Color
		{
			
			if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_BLACK) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_BLACK;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_BLUE) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_BLUE;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_BROWN) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_BROWN;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_GRAY) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_GRAY;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_GREEN) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_GREEN;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_ORANGE) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_ORANGE;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_PINK) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_PINK;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_PURPLE) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_PURPLE;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_RED) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_RED;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_TEAL) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_TEAL;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_WHITE) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_WHITE;
			} else if (strImageColor.equalsIgnoreCase(STR_VAL_IMAGE_COLOR_YELLOW) == true) {
				mOptImageColor = STR_VAL_IMAGE_COLOR_YELLOW;
			}
		}

		// Image Type
		{
			if (strImageType.equalsIgnoreCase(STR_VAL_IMAGE_TYPE_FACE) == true) {
				mOptImageType = STR_VAL_IMAGE_TYPE_FACE;
			} else if (strImageType.equalsIgnoreCase(STR_VAL_IMAGE_TYPE_PHOTO) == true) {
				mOptImageType = STR_VAL_IMAGE_TYPE_PHOTO;
			} else if (strImageType.equalsIgnoreCase(STR_VAL_IMAGE_TYPE_CLIPART) == true) {
				mOptImageType = STR_VAL_IMAGE_TYPE_CLIPART;
			} else if (strImageType.equalsIgnoreCase(STR_VAL_IMAGE_TYPE_LINEART) == true) {
				mOptImageType = STR_VAL_IMAGE_TYPE_LINEART;
			}
		}

		// File Type
		{
			if (strFileType.equalsIgnoreCase(STR_VAL_FILE_TYPE_BMP) == true) {
					mOptFileType = STR_VAL_FILE_TYPE_BMP;
			} else if (strFileType.equalsIgnoreCase(STR_VAL_FILE_TYPE_GIF) == true) {
					mOptFileType = STR_VAL_FILE_TYPE_GIF;
			} else if (strFileType.equalsIgnoreCase(STR_VAL_FILE_TYPE_JPG) == true) {
					mOptFileType = STR_VAL_FILE_TYPE_JPG;
			} else if (strFileType.equalsIgnoreCase(STR_VAL_FILE_TYPE_PNG) == true) {
					mOptFileType = STR_VAL_FILE_TYPE_PNG;
			}
		}

		// Site Search
		{
			if (sSiteSerach != "") {
				mOptSitSearch = sSiteSerach;
			}
		}
	}

	public void setOptParam(int iImageSize, int iImageColor, int iImageType, String sSiteSerach, int iFileType) {
		// Image Size
		{
			if ((iImageSize >= indxImageSizeMin)
					&& (iImageSize <= indxImageSizeMax)) {
				if (iImageSize == indxImageSizeSmall) {
					mOptImageSize = STR_VAL_IMAGE_SIZE_SMALL;
				} else if (iImageSize == indxImageSizeMedium) {
					mOptImageSize = STR_VAL_IMAGE_SIZE_MEDIUM;
				} else if (iImageSize == indxImageSizeLarge) {
					mOptImageSize = STR_VAL_IMAGE_SIZE_LARGE;
				} else if (iImageSize == indxImageSizeExtraLarge) {
					mOptImageSize = STR_VAL_IMAGE_SIZE_EXTRA_LARGE;
				}
			}
		}

		// Image Color
		{
			if ((iImageColor >= indxImageColorMin)
					&& (iImageColor <= indxImageColorMax)) {
				if (iImageColor == indxImageColorBlack) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_BLACK;
				} else if (iImageColor == indxImageColorBlue) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_BLUE;
				} else if (iImageColor == indxImageColorBrown) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_BROWN;
				} else if (iImageColor == indxImageColorGray) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_GRAY;
				} else if (iImageColor == indxImageColorGreen) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_GREEN;
				} else if (iImageColor == indxImageColorOrange) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_ORANGE;
				} else if (iImageColor == indxImageColorPink) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_PINK;
				} else if (iImageColor == indxImageColorPurple) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_PURPLE;
				} else if (iImageColor == indxImageColorRed) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_RED;
				} else if (iImageColor == indxImageColorTeal) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_TEAL;
				} else if (iImageColor == indxImageColorWhite) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_WHITE;
				} else if (iImageColor == indxImageColorYellow) {
					mOptImageColor = STR_VAL_IMAGE_COLOR_YELLOW;
				}
			}
		}

		// Image Type
		{
			if ((iImageType >= indxImageTypeMin)
					&& (iImageType <= indxImageTypeMax)) {
				if (iImageType == indxImageTypeFace) {
					mOptImageType = STR_VAL_IMAGE_TYPE_FACE;
				} else if (iImageType == indxImageTypePhoto) {
					mOptImageType = STR_VAL_IMAGE_TYPE_PHOTO;
				} else if (iImageType == indxImageTypeClipArt) {
					mOptImageType = STR_VAL_IMAGE_TYPE_CLIPART;
				} else if (iImageType == indxImageTypeLineArt) {
					mOptImageType = STR_VAL_IMAGE_TYPE_LINEART;
				}
			}
		}

		// File Type
		{
			if ((iFileType >= indxFileTypeMin)
					&& (iFileType <= indxFileTypeMax)) {
				if (iFileType == indxFileTypeBmp) {
					mOptFileType = STR_VAL_FILE_TYPE_BMP;
				} else if (iFileType == indxFileTypeGif) {
					mOptFileType = STR_VAL_FILE_TYPE_GIF;
				} else if (iFileType == indxFileTypeJpg) {
					mOptFileType = STR_VAL_FILE_TYPE_JPG;
				} else if (iFileType == indxFileTypePng) {
					mOptFileType = STR_VAL_FILE_TYPE_PNG;
				}
			}
		}

		// Site Search
		{
			if (sSiteSerach != "") {
				mOptSitSearch = sSiteSerach;
			}
		}
	}

	// Create URL
	public String createUrl(String strQuery, int iResPerPage, int iResOffset) {
		String urlSearchAPIUrl = "";

		if (strQuery == "")
			return "";

		// https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=fuzzy%20monkey
		// Mandatory params
		{
			urlSearchAPIUrl = BASE_URL + "?" + KEY_VAL_VERSION + "&"
					+ KEY_QUERY + strQuery;
		}
		// Max 8 Results at a time and offset param
		{
			urlSearchAPIUrl = urlSearchAPIUrl + "&" + KEY_RESULT_PER_PAGE + iResPerPage;
			urlSearchAPIUrl = urlSearchAPIUrl + "&" + KEY_OFFSET + iResOffset;
		}

		{
			if (mOptImageSize != "") {
				urlSearchAPIUrl = urlSearchAPIUrl + "&" + KEY_IMAGE_SIZE + mOptImageSize;
			}

			if (mOptImageColor != "") {
				urlSearchAPIUrl = urlSearchAPIUrl + "&" + KEY_IMAGE_COLOR + mOptImageColor;
			}

			if (mOptImageType != "") {
				urlSearchAPIUrl = urlSearchAPIUrl + "&" + KEY_IAMGE_TYPE + mOptImageType;
			}

			if (mOptSitSearch != "") {
				urlSearchAPIUrl = urlSearchAPIUrl + "&" + KEY_SITE_SEARCH + mOptSitSearch;
			}

			if (mOptFileType != "") {
				urlSearchAPIUrl = urlSearchAPIUrl + "&" + KEY_FILE_TYPE + mOptFileType;
			}
		}

		System.out.println(" URL : " + urlSearchAPIUrl);
		return urlSearchAPIUrl;
	}
}
