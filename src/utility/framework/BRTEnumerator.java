package utility.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import logger.BIRT_Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.anugraha.birt.app.BIRT_AppProperty;

import comm.BIRT_DataObject;
import exception.BIRT_Exception;
import exception.BIRT_Temp_Exception;

public class BRTEnumerator {
	private static String[] straTypeEnumerator = { "String", "Boolean",
			"Integer", "Float", "Date", "Double" };

	public static int[] getEnumerator(File fFiletoEnumerate)
			throws BIRT_Exception {

		try {
			Scanner sc;

			sc = new Scanner(fFiletoEnumerate);

			int iLineCnt = 0;
			while (sc.hasNextLine()) {
				sc.nextLine();
				iLineCnt++;
			}
			sc.close();

			sc = new Scanner(fFiletoEnumerate);

			int[] iaEnumeratedList = new int[iLineCnt];

			int iaEnCtr = 0;
			int iSTECnt = straTypeEnumerator.length;

			while (sc.hasNextLine()) {
				String strType = sc.nextLine();
				int iEnumCtr = 0;
				for (; iEnumCtr < iSTECnt; iEnumCtr++)
					if (strType.equals(straTypeEnumerator[iEnumCtr])) {
						iaEnumeratedList[iaEnCtr] = iEnumCtr;
						break;
					}
				if (iEnumCtr == iSTECnt) {
					sc.close();
					throw new BIRT_Temp_Exception(
							"Type Mismatch in Source/Target data preventing from Comparison of Data");
				}
				iaEnCtr++;
			}
			sc.close();
			return iaEnumeratedList;

		} catch (FileNotFoundException e) {
			if (BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to Edit Report File.");
		}

	}

	@SuppressWarnings("deprecation")
	public static void writeEnumertedSourcetoCell(Row rowCurrentSheet,
			Cell cellCurrentSheet, String strValue, int[] iaEnumerator,
			int iIndex) throws BIRT_Exception {
		if (cellCurrentSheet == null)
			cellCurrentSheet = rowCurrentSheet.createCell(iIndex);

		if (strValue != null) {
			if (!strValue.equals("")) {
				// private static String[]
				// { "String", "Boolean",
				// "Integer","Float","Date", "Double" };

				if (iaEnumerator[iIndex] == 0)
					cellCurrentSheet.setCellValue(strValue.trim());
				else if (iaEnumerator[iIndex] == 1)
					cellCurrentSheet.setCellValue(Boolean.parseBoolean(strValue
							.trim()));
				else if (iaEnumerator[iIndex] == 2)
					cellCurrentSheet.setCellValue(Integer.parseInt(strValue
							.trim().replace(",", "")));
				else if (iaEnumerator[iIndex] == 3)
					cellCurrentSheet.setCellValue(Float.parseFloat(strValue
							.trim().replace(",", "")));
				else if (iaEnumerator[iIndex] == 4) {
					DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

					try {
						cellCurrentSheet.setCellValue(df.parse(strValue));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (iaEnumerator[iIndex] == 5)
					cellCurrentSheet.setCellValue(Double.parseDouble(strValue
							.trim().replace(",", "")));
				throw new BIRT_Temp_Exception(
						"Type Mismatch in Source/Target data preventing from Comparison of Data");
			} else {
				cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
			}

		}

	}

	@SuppressWarnings("deprecation")
	public static void writeAutoMetadataSrcToCell(Row rowCurrentSheet,
			Cell cellCurrentSheet, String strValue, int iIndex)
			throws BIRT_Exception {

		try {
			// second
//			int iColumnType = BIRT_DataObject.getRsmtdCurrentQueryResult()
//					.getColumnType(iIndex);

			if (cellCurrentSheet == null)
				cellCurrentSheet = rowCurrentSheet.createCell(iIndex - 1);

			if (strValue != null && !strValue.equals("")) {
				// private static String[]
				// { "String", "Boolean",
				// "Integer","Float","Date", "Double" };
				cellCurrentSheet.setCellValue(strValue);
				
//				if (iColumnType == Types.VARCHAR
//						|| iColumnType == Types.NVARCHAR
//						|| iColumnType == Types.NCHAR
//						|| iColumnType == Types.LONGVARCHAR
//						|| iColumnType == Types.LONGNVARCHAR
//						|| iColumnType == Types.CHAR) {
//					cellCurrentSheet.setCellValue(strValue);
//				} else if (iColumnType == Types.BOOLEAN) {
//					cellCurrentSheet.setCellValue(Boolean.parseBoolean(strValue
//							.trim()));
//				}
//
//				else if (iColumnType == Types.BIGINT
//						|| iColumnType == Types.BIT
//						|| iColumnType == Types.INTEGER
//						|| iColumnType == Types.SMALLINT
//						|| iColumnType == Types.TINYINT) {
//					cellCurrentSheet.setCellValue(Integer.parseInt(strValue
//							.trim().replace(",", "")));
//				}
//
//				else if (iColumnType == Types.FLOAT) {
//					cellCurrentSheet.setCellValue(Float.parseFloat(strValue
//							.trim().replace(",", "")));
//				}
//
//				else if (iColumnType == Types.DATE
//						|| iColumnType == Types.TIMESTAMP
//						|| iColumnType == Types.TIME) {
//					/*
//					 * DateFormat df = new SimpleDateFormat("MM-DD-YYYY");
//					 * 
//					 * cellCurrentSheet.setCellValue(df.parse(strValue));
//					 */
//					cellCurrentSheet.setCellValue(Date.parse(strValue.trim()));
//				}
//
//				else if (iColumnType == Types.DECIMAL
//						|| iColumnType == Types.DOUBLE
//						|| iColumnType == Types.NUMERIC) {
//					cellCurrentSheet.setCellValue(Double.parseDouble(strValue
//							.trim().replace(",", "")));
//				}
//
//				else
//
//					throw new BIRT_Temp_Exception(
//							"Type Mismatch in Source/Target data preventing from Comparison of Data");

			} else if (strValue.equals("")) {
				cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
			}
//		} catch (SQLException e) {
		} catch (Exception e) {
			if (BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);

			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to connect to Database");
		}

	}

	public static void writeEnumertedTrgtToCell(Row rowCurrentSheet,
			Cell cellCurrentSheet, ResultSet rsQueryResult, int[] iaEnumerator,
			int iIndex) throws BIRT_Exception {
		try {

			if (cellCurrentSheet == null)
				cellCurrentSheet = rowCurrentSheet.createCell(iIndex);

			if (rsQueryResult != null) {
				// private static String[]
				// { "String", "Boolean",
				// "Integer","Float","Date", "Double" };

				if (iaEnumerator[iIndex] == 0) {
					String strValue;

					strValue = rsQueryResult.getString(iIndex).trim();

					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(strValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				} else if (iaEnumerator[iIndex] == 1) {
					boolean blValue = rsQueryResult.getBoolean(iIndex);
					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(blValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else if (iaEnumerator[iIndex] == 2) {
					int iValue = rsQueryResult.getInt(iIndex);
					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(iValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else if (iaEnumerator[iIndex] == 3) {
					float fValue = rsQueryResult.getFloat(iIndex);
					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(fValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else if (iaEnumerator[iIndex] == 4) {
					Date dtValue = rsQueryResult.getDate(iIndex);
					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(dtValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else if (iaEnumerator[iIndex] == 5) {
					double dValue = rsQueryResult.getDouble(iIndex);
					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(dValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else {

					BIRT_Logger
							.error("Type Mismatch in Source/Target data preventing from Comparison of Data");
					throw new BIRT_Temp_Exception(
							"Type Mismatch in Source/Target data preventing from Comparison of Data");
				}

			}
		} catch (SQLException e) {
			if (BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to connect to Database");
		}

	}

	public static void writeAutoMetadataSrcToCellFromExcel(Row rowCurrentSheet,
			Cell cellCurrentSheet, Cell cellReportSheet, int iIndex)
			throws Exception {
		try {
				// System.out.println("Friday1: " + BIRT_DataObject.getRsmtdCurrentQueryResult());
				// System.out.println("Friday2: " + iIndex);
				// System.out.println("Friday3: " + BIRT_DataObject.getRsmtdCurrentQueryResult().getColumnType(iIndex));
				
				// It uses the type from Teradata here
				// But it will cause problem if they are not match
				// I changed it to the type from Excel
//				int iColumnType = BIRT_DataObject.getRsmtdCurrentQueryResult()
//						.getColumnType(iIndex);
				int iColumnType = cellReportSheet.getCellType();
				
				if (cellCurrentSheet == null){
					// add one outside, but minus one inside the function here?
					System.out.println("cellCurrentSheet is null.....");
					cellCurrentSheet = rowCurrentSheet.createCell(iIndex - 1);
				}
				if (cellReportSheet.getCellType() != Cell.CELL_TYPE_BLANK) {
					if (iColumnType == Types.VARCHAR
							|| iColumnType == Types.NVARCHAR
							|| iColumnType == Types.NCHAR
							|| iColumnType == Types.LONGVARCHAR
							|| iColumnType == Types.LONGNVARCHAR
							|| iColumnType == Types.CHAR) {
						cellCurrentSheet.setCellValue(cellReportSheet
								.getStringCellValue());
					} else if (iColumnType == Types.BOOLEAN) {
						cellCurrentSheet.setCellValue(cellReportSheet
								.getBooleanCellValue());
					} else if (iColumnType == Types.BIGINT
							|| iColumnType == Types.BIT
							|| iColumnType == Types.INTEGER
							|| iColumnType == Types.SMALLINT
							|| iColumnType == Types.TINYINT) {
						cellCurrentSheet.setCellValue(cellReportSheet
								.getNumericCellValue());
					} else if (iColumnType == Types.FLOAT) {
						cellCurrentSheet.setCellValue(cellReportSheet
								.getNumericCellValue());
					} else if (iColumnType == Types.DATE
							|| iColumnType == Types.TIMESTAMP
							|| iColumnType == Types.TIME) {
						cellCurrentSheet.setCellValue(cellReportSheet
								.getDateCellValue());
					} else if (iColumnType == Types.DECIMAL
							|| iColumnType == Types.DOUBLE
							|| iColumnType == Types.NUMERIC) {
						cellCurrentSheet.setCellValue(cellReportSheet
								.getNumericCellValue());
					} else
						throw new BIRT_Temp_Exception(
								"Type Mismatch in Source/Target data preventing from Comparison of Data");
			} else {
				// Zekun_change
				// System.out.println(cellReportSheet.getCellType());
				cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void writeAutoMetadataTrgtToCell(Row rowCurrentSheet,
			Cell cellCurrentSheet, ResultSet rsQueryResult, int iIndex)
			throws BIRT_Exception {
		try {
			int iColumnType = BIRT_DataObject.getRsmtdCurrentQueryResult()
					.getColumnType(iIndex);

			if (cellCurrentSheet == null)
				cellCurrentSheet = rowCurrentSheet.createCell(iIndex - 1);

			if (rsQueryResult != null) {
				// private static String[]
				// { "String", "Boolean",
				// "Integer","Float","Date", "Double" };

				// System.out.println("Coulumn Type : " + iColumnType);
				// System.out.println("Varchar : " + Types.INTEGER);

				if (iColumnType == Types.VARCHAR
						|| iColumnType == Types.NVARCHAR
						|| iColumnType == Types.NCHAR
						|| iColumnType == Types.LONGVARCHAR
						|| iColumnType == Types.LONGNVARCHAR
						|| iColumnType == Types.CHAR) {
					String strValue;
					if (iColumnType == Types.VARCHAR
							|| iColumnType == Types.LONGVARCHAR
							|| iColumnType == Types.CHAR)
						strValue = rsQueryResult.getString(iIndex);
					else
						strValue = rsQueryResult.getNString(iIndex);

					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(strValue.trim());
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				} else if (iColumnType == Types.BOOLEAN) {
					boolean blValue = rsQueryResult.getBoolean(iIndex);
					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(blValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else if (iColumnType == Types.BIGINT
						|| iColumnType == Types.BIT
						|| iColumnType == Types.INTEGER
						|| iColumnType == Types.SMALLINT
						|| iColumnType == Types.TINYINT) {
					int iValue = rsQueryResult.getInt(iIndex);
					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(iValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else if (iColumnType == Types.FLOAT) {
					float fValue = rsQueryResult.getFloat(iIndex);
					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(fValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else if (iColumnType == Types.DATE
						|| iColumnType == Types.TIMESTAMP
						|| iColumnType == Types.TIME) {
					Date dtValue;
					if (iColumnType == Types.TIME)
						dtValue = rsQueryResult.getTime(iIndex);
					else if (iColumnType == Types.TIMESTAMP)
						dtValue = rsQueryResult.getTimestamp(iIndex);
					else
						dtValue = rsQueryResult.getDate(iIndex);
					if (!rsQueryResult.wasNull()) {
						DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
						
						// System.out.println("dtValue: " + df.format(dtValue));
						// Zekun_change
						// System.out.println("Zekun_change");
						//cellCurrentSheet.setCellValue(df.parse(dtValue.toString()));
						cellCurrentSheet.setCellValue(df.format(dtValue));
					} else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else if (iColumnType == Types.DECIMAL
						|| iColumnType == Types.DOUBLE
						|| iColumnType == Types.NUMERIC) {
					double dValue = rsQueryResult.getDouble(iIndex);
					if (!rsQueryResult.wasNull())
						cellCurrentSheet.setCellValue(dValue);
					else
						cellCurrentSheet.setCellValue(BIRT_DataObject.NULL);
				}

				else

					throw new BIRT_Temp_Exception(
							"Type Mismatch in Source/Target data preventing from Comparison of Data");

			}
		} catch (SQLException e) {
			if (BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to connect to Database");
		// Zekun_change
		// from ParseException to Exception
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (BIRT_AppProperty.PRINT_STACK_TRACE)
				BIRT_Logger.printStackTrace(e);
			BIRT_Logger.error(e.getMessage());
			throw new BIRT_Temp_Exception("Unable to parse date");
		}

	}
}
