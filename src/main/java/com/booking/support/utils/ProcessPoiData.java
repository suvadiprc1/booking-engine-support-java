package com.booking.support.utils;

import com.booking.support.dtos.DistanceDetails;
import com.booking.support.dtos.Location;
import com.booking.support.dtos.PointOfInterest;
import com.booking.support.dtos.PromotionalOffer;
import com.booking.support.dtos.PromotionalRule;
import com.booking.support.exceptions.PoiApplException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProcessPoiData {
	
    public static Map<Location, List<PointOfInterest>> populatePointOfInterestMap() {

        final Map<Location, List<PointOfInterest>> pointOfInterestMap = new HashMap<Location, List<PointOfInterest>>();
        List<PointOfInterest> pointOfInterests = new ArrayList<PointOfInterest>();

        try {
            final XSSFSheet sheet = getWorkBookSheet(PointOfInterestConstant.POI_RESOURCE);

            final Map<String, Integer> headerMap = getHeaderMap(sheet);
            final int latIndx = headerMap.get(PointOfInterestConstant.LAT);
            final int longIndx = headerMap.get(PointOfInterestConstant.LONG);
            final int driveDistanceIndx = headerMap.get(PointOfInterestConstant.DRIVE_DISTANCE);
            final int promotionalOfferIndx = headerMap.get(PointOfInterestConstant.PROMOTIONAL_OFFER);
            final int nameIndx = headerMap.get(PointOfInterestConstant.NAME);
            final int specialInstructionIndx = headerMap.get(PointOfInterestConstant.SPECIAL_INSTRUCTIONS);

            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();
            // Traversing over each row of XLSX file
            while (rowIterator.hasNext()) {
                final Row row = rowIterator.next();
                if (row.getRowNum() != 0) {

                    final Location loc = new Location();
                    loc.setLatitude(row.getCell(latIndx).getNumericCellValue());
                    loc.setLongitude(row.getCell(longIndx).getNumericCellValue());
                    if (pointOfInterestMap.containsKey(loc)) {
                        pointOfInterests = pointOfInterestMap.get(loc);
                    } else {
                        pointOfInterests = new ArrayList<PointOfInterest>();
                    }

                    final PointOfInterest pointOfInterest =
                        addPointOfInterest(driveDistanceIndx, promotionalOfferIndx, nameIndx, specialInstructionIndx, row);

                    pointOfInterests.add(pointOfInterest);
                    pointOfInterestMap.put(loc, pointOfInterests);
                }

            }

        } catch (FileNotFoundException e) {

            throw new PoiApplException(PointOfInterestConstant.FILE_NOT_FOUND_LOCATION);
        } catch (IOException e) {

            throw new PoiApplException(PointOfInterestConstant.ERROR_READING_FILE);
        }

        return pointOfInterestMap;

    }

    private static PointOfInterest addPointOfInterest(final int driveDistanceIndx, final int promotionalOfferIndx,
        final int nameIndx, final int specialInstructionIndx, final Row row) {
        final PointOfInterest pointOfInterest = new PointOfInterest();

        pointOfInterest.setName(row.getCell(nameIndx).getStringCellValue());
        pointOfInterest.setSpecialInstructions(row.getCell(specialInstructionIndx).getStringCellValue());
        final Double driveDistanceId = row.getCell(driveDistanceIndx).getNumericCellValue();
        final DistanceDetails driveDistance = new DistanceDetails();
        populateDriveDistance(driveDistanceId, driveDistance);
        pointOfInterest.setDriveDistance(driveDistance);
        final PromotionalOffer offer = new PromotionalOffer();
        populatePromotionalOffer(row.getCell(promotionalOfferIndx).getNumericCellValue(), offer);
        pointOfInterest.setPromoOffers(offer);
        return pointOfInterest;
    }

    public static DistanceDetails populateDriveDistance(final double driveDistanceId, final DistanceDetails driveDistance) {

        try {
            final XSSFSheet sheet = getWorkBookSheet(PointOfInterestConstant.POI_DRIVE_RESOURCE);

            final Map<String, Integer> headerMap = getHeaderMap(sheet);
            final int driveDistanceIdIndx = headerMap.get(PointOfInterestConstant.DRIVE_DISTANCE_ID);
            final int latIndx = headerMap.get(PointOfInterestConstant.LAT);
            final int longIndx = headerMap.get(PointOfInterestConstant.LONG);
            final int driveDistanceIndx = headerMap.get(PointOfInterestConstant.DRIVE_DISTANCE);
            final int landmarkInstructionsIndx = headerMap.get(PointOfInterestConstant.LANDMARK_INSTRUCTIONS);
            final int descriptionIndx = headerMap.get(PointOfInterestConstant.DESCR);
            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();
            // Traversing over each row of XLSX file
            while (rowIterator.hasNext()) {
                final Row row = rowIterator.next();
                if (row.getRowNum() != 0) {

                    final double driveDistanceIdVal = row.getCell(driveDistanceIdIndx).getNumericCellValue();
                    if (driveDistanceIdVal == driveDistanceId) {
                        driveDistance.setDriveDistance(String.valueOf(row.getCell(driveDistanceIndx).getNumericCellValue()));
                        driveDistance.setLandMarkInstructions(row.getCell(landmarkInstructionsIndx).getStringCellValue());
                        driveDistance.setLatitude(row.getCell(latIndx).getNumericCellValue());
                        driveDistance.setLongitude(row.getCell(longIndx).getNumericCellValue());
                        driveDistance.setDescription(row.getCell(descriptionIndx).getStringCellValue());
                        break;
                    }
                }

            }
        } catch (FileNotFoundException e) {

            throw new PoiApplException(PointOfInterestConstant.FILE_NOT_FOUND_LOCATION);
        } catch (IOException e) {

            throw new PoiApplException(PointOfInterestConstant.ERROR_READING_FILE);
        }

        return driveDistance;
    }

    public static PromotionalOffer populatePromotionalOffer(final double promotionalOfferId,
        final PromotionalOffer promotionalOffer) {

        try {
            final XSSFSheet sheet = getWorkBookSheet(PointOfInterestConstant.POI_PROMOTION_OFFER_RESOURCE);
            final Map<String, Integer> headerMap = getHeaderMap(sheet);
            final int promoOfferIdIndx = headerMap.get(PointOfInterestConstant.PROMO_OFFER_ID);
            final int loyaltyCodeIndx = headerMap.get(PointOfInterestConstant.LOYALTY_CODE);
            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();
            // Traversing over each row of XLSX file
            while (rowIterator.hasNext()) {
                final Row row = rowIterator.next();
                if (row.getRowNum() != 0) {
                    final double promoOfferIdValue = row.getCell(promoOfferIdIndx).getNumericCellValue();
                    if (promoOfferIdValue == promotionalOfferId) {
                        final List<PromotionalRule> populatePromotionalRules = populatePromotionalRules(promotionalOfferId);
                        promotionalOffer.setRules(populatePromotionalRules);
                        promotionalOffer.setLoyaltyCode(row.getCell(loyaltyCodeIndx).getStringCellValue());
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {

            throw new PoiApplException(PointOfInterestConstant.FILE_NOT_FOUND_LOCATION);
        } catch (IOException e) {

            throw new PoiApplException(PointOfInterestConstant.ERROR_READING_FILE);
        }
        return promotionalOffer;
    }

    public static List<PromotionalRule> populatePromotionalRules(final double promotionalOfferId) {
        final List<PromotionalRule> promotionalRules = new ArrayList<PromotionalRule>();

        try {
            final XSSFSheet sheet = getWorkBookSheet(PointOfInterestConstant.POI_PROMOTION_RULE_RESOURCE);
            final Map<String, Integer> headerMap = getHeaderMap(sheet);
            final int promoCodeIdIndx = headerMap.get(PointOfInterestConstant.PROMO_CODE_ID);
            final int promoOfferIdIndx = headerMap.get(PointOfInterestConstant.PROMO_OFFER_ID);
            final int discountIndx = headerMap.get(PointOfInterestConstant.DISCOUNT);
            final int discountTypeIndx = headerMap.get(PointOfInterestConstant.DISCOUNT_TYPE);
            final int descrIndx = headerMap.get(PointOfInterestConstant.DESCR);
            final int categoryIndx = headerMap.get(PointOfInterestConstant.CATEGORY);

            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();
            // Traversing over each row of XLSX file
            while (rowIterator.hasNext()) {
                final Row row = rowIterator.next();
                if (row.getRowNum() != 0) {
                    final double promoOfferIdValue = row.getCell(promoOfferIdIndx).getNumericCellValue();
                    if (promoOfferIdValue == promotionalOfferId) {
                        final PromotionalRule promotionalRule = new PromotionalRule();
                        promotionalRule.setCategory(row.getCell(categoryIndx).getStringCellValue());
                        promotionalRule.setDescription(row.getCell(descrIndx).getStringCellValue());
                        promotionalRule.setDiscount(row.getCell(discountIndx).getNumericCellValue());
                        promotionalRule.setDiscountType(row.getCell(discountTypeIndx).getStringCellValue());
                        final Double promoCodeId = row.getCell(promoCodeIdIndx).getNumericCellValue();
                        promotionalRule.setPromoCodeId(String.valueOf(promoCodeId.intValue()));
                        promotionalRules.add(promotionalRule);
                    }

                }
            }

        } catch (FileNotFoundException e) {

            throw new PoiApplException(PointOfInterestConstant.FILE_NOT_FOUND_LOCATION);
        } catch (IOException e) {

            throw new PoiApplException(PointOfInterestConstant.ERROR_READING_FILE);
        }
        return promotionalRules;
    }

    private static XSSFSheet getWorkBookSheet(final String resourcePath) throws IOException, FileNotFoundException {
    	final InputStream inputStream = ProcessPoiData.class.getResourceAsStream(resourcePath);
        // Finds the workbook instance for XLSX file
        final XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
        // Return first sheet from the XLSX workbook
        final XSSFSheet sheet = workBook.getSheetAt(0);
        return sheet;
    }

    private static Map<String, Integer> getHeaderMap(final XSSFSheet sheet) {
        final XSSFRow headerRow = sheet.getRow(0);
        final int firstColumnIndex = headerRow.getFirstCellNum();
        final int lastColumnIndex = headerRow.getLastCellNum();
        final Map<String, Integer> headerMap = new HashMap<String, Integer>(); // Create map

        for (int colIx = firstColumnIndex; colIx < lastColumnIndex; colIx++) { // loop from first to last index
            Cell cell = headerRow.getCell(colIx); // get the cell
            headerMap.put(cell.getStringCellValue(), cell.getColumnIndex()); // add the cell contents (name of column) and cell
                                                                             // index to the map
        }
        return headerMap;
    }
   
}
