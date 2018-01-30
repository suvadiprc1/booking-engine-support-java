package com.booking.support.utils;

import com.booking.support.dtos.DistanceDetails;
import com.booking.support.dtos.PromotionalOffer;
import com.booking.support.dtos.PromotionalRule;
import com.booking.support.dtos.Reservation;
import com.booking.support.exceptions.ReservationApplException;
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

public class ProcessReservationData {
    public static List<String> getReservationIds(final String memberIdReq, final Double latitude, final Double longitude) {
        List<String> reservationIds = new ArrayList<String>();

        try {
            final XSSFSheet sheet = getWorkBookSheet(ReservationConstant.RESERVATION_MEMBER_RESOURCE);

            final Map<String, Integer> headerMap = getHeaderMap(sheet);
            final int memIdIndx = headerMap.get(ReservationConstant.MEMBER_ID);
            final int latIndx = headerMap.get(ReservationConstant.LAT);
            final int longIndx = headerMap.get(ReservationConstant.LONG);
            final int reservationIdIndx = headerMap.get(ReservationConstant.RESERVATION_ID);

            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();
            // Traversing over each row of XLSX file
            while (rowIterator.hasNext()) {
                final Row row = rowIterator.next();
                if (row.getRowNum() != 0) {
                    final Cell memberIdCell = row.getCell(memIdIndx);
                    final Cell latCell = row.getCell(latIndx);
                    final Cell longCell = row.getCell(longIndx);
                    final Cell reservationIdCell = row.getCell(reservationIdIndx);
                    final Double memberIdNumValue = memberIdCell.getNumericCellValue();
                    final String memberId = String.valueOf(memberIdNumValue.intValue());
                    final Double latValue = latCell.getNumericCellValue();
                    final Double longValue = longCell.getNumericCellValue();
					final Double reservationIdValue = reservationIdCell.getNumericCellValue();
                        reservationIds.add(String.valueOf(reservationIdValue.intValue()));                    
                }

            }
        } catch (FileNotFoundException e) {

            throw new ReservationApplException(ReservationConstant.FILE_NOT_FOUND_LOCATION);
        } catch (IOException e) {

            throw new ReservationApplException(ReservationConstant.ERROR_READING_FILE);
        }

        return reservationIds;
    }

    public static Map<String, Reservation> populateReservationMap() {

        final Map<String, Reservation> reservationMap = new HashMap<String, Reservation>();

        try {
            final XSSFSheet sheet = getWorkBookSheet(ReservationConstant.RESERVATION_RESOURCE);

            final Map<String, Integer> headerMap = getHeaderMap(sheet);
            final int reservationIdIndx = headerMap.get(ReservationConstant.RESERVATION_ID);
            final int startTimeIndx = headerMap.get(ReservationConstant.START_DATE_TIME);
            final int endTimeIndx = headerMap.get(ReservationConstant.END_DATE_TIME);
            final int categoryIndx = headerMap.get(ReservationConstant.CATEGORY);
            final int bookingPriceIndx = headerMap.get(ReservationConstant.BOOKING_PRICE);
            final int driveDistanceIndx = headerMap.get(ReservationConstant.DRIVE_DISTANCE);
            final int promotionalOfferIndx = headerMap.get(ReservationConstant.PROMOTIONAL_OFFER);
            final int companyNameIndx = headerMap.get(ReservationConstant.COMPANY_NAME);
            final int companyWebsiteIndx = headerMap.get(ReservationConstant.COMPANY_WEBSITE);
            final int specialInstructionsIndx = headerMap.get(ReservationConstant.SPECIAL_INSTRUCTIONS);
            final int resourceDescriptionIndx = headerMap.get(ReservationConstant.RESOURCE_DESCRIPTION);
            final int vehicleNoIndx = headerMap.get(ReservationConstant.VEHICLE_NO);
            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();
            // Traversing over each row of XLSX file
            while (rowIterator.hasNext()) {
                final Row row = rowIterator.next();
                if (row.getRowNum() != 0) {
                    final Reservation reservation = new Reservation();

                    //reservation.setBookingPrice(new DataFormatter().formatCellValue(row.getCell(bookingPriceIndx)));
                    reservation.setBookingPrice(String.valueOf(row.getCell(bookingPriceIndx).getNumericCellValue()));
                    reservation.setCategory(row.getCell(categoryIndx).getStringCellValue());
                    reservation.setCompanyName(row.getCell(companyNameIndx).getStringCellValue());
                    reservation.setCompanyWebsite(row.getCell(companyWebsiteIndx).getStringCellValue());
                    reservation.setStartDateTime(row.getCell(startTimeIndx).getNumericCellValue());
                    reservation.setEndDateTime(row.getCell(endTimeIndx).getNumericCellValue());
                    reservation.setResourceDescription(row.getCell(resourceDescriptionIndx).getStringCellValue());
                    reservation.setVehicleNo(row.getCell(vehicleNoIndx).getStringCellValue());
                    final Double reservationIdValue = row.getCell(reservationIdIndx).getNumericCellValue();
                    final String reservationId = String.valueOf(reservationIdValue.intValue());
                    reservation.setReservationId(reservationId);
                    reservation.setSpecialInstructions(row.getCell(specialInstructionsIndx).getStringCellValue());
                    final Double driveDistanceId = row.getCell(driveDistanceIndx).getNumericCellValue();
                    final DistanceDetails driveDistance = new DistanceDetails();
                    populateDriveDistance(driveDistanceId, driveDistance);
                    reservation.setDriveDistance(driveDistance);
                    final PromotionalOffer offer = new PromotionalOffer();
                    populatePromotionalOffer(row.getCell(promotionalOfferIndx).getNumericCellValue(), offer);
                    reservation.setPromoOffers(offer);
                    reservationMap.put(reservationId, reservation);
                }

            }

        } catch (FileNotFoundException e) {

            throw new ReservationApplException(ReservationConstant.FILE_NOT_FOUND_LOCATION);
        } catch (IOException e) {

            throw new ReservationApplException(ReservationConstant.ERROR_READING_FILE);
        }

        return reservationMap;

    }

    public static DistanceDetails populateDriveDistance(final double driveDistanceId, final DistanceDetails driveDistance) {

        try {
            final XSSFSheet sheet = getWorkBookSheet(ReservationConstant.RESERVATION_DRIVE_RESOURCE);

            final Map<String, Integer> headerMap = getHeaderMap(sheet);
            final int driveDistanceIdIndx = headerMap.get(ReservationConstant.DRIVE_DISTANCE_ID);
            final int latIndx = headerMap.get(ReservationConstant.LAT);
            final int longIndx = headerMap.get(ReservationConstant.LONG);
            final int driveDistanceIndx = headerMap.get(ReservationConstant.DRIVE_DISTANCE);
            final int landmarkInstructionsIndx = headerMap.get(ReservationConstant.LANDMARK_INSTRUCTIONS);
            final int descriptionIndx = headerMap.get(ReservationConstant.DESCR);

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

            throw new ReservationApplException(ReservationConstant.FILE_NOT_FOUND_LOCATION);
        } catch (IOException e) {

            throw new ReservationApplException(ReservationConstant.ERROR_READING_FILE);
        }

        return driveDistance;
    }

    public static PromotionalOffer populatePromotionalOffer(final double promotionalOfferId,
        final PromotionalOffer promotionalOffer) {

        try {
            final XSSFSheet sheet = getWorkBookSheet(ReservationConstant.RESERVATION_PROMOTION_OFFER_RESOURCE);
            final Map<String, Integer> headerMap = getHeaderMap(sheet);
            final int promoOfferIdIndx = headerMap.get(ReservationConstant.PROMO_OFFER_ID);
            final int loyaltyCodeIndx = headerMap.get(ReservationConstant.LOYALTY_CODE);
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

            throw new ReservationApplException(ReservationConstant.FILE_NOT_FOUND_LOCATION);
        } catch (IOException e) {

            throw new ReservationApplException(ReservationConstant.ERROR_READING_FILE);
        }
        return promotionalOffer;
    }

    public static List<PromotionalRule> populatePromotionalRules(final double promotionalOfferId) {
        final List<PromotionalRule> promotionalRules = new ArrayList<PromotionalRule>();

        try {
            final XSSFSheet sheet = getWorkBookSheet(ReservationConstant.RESERVATION_PROMOTION_RULE_RESOURCE);
            final Map<String, Integer> headerMap = getHeaderMap(sheet);
            final int promoCodeIdIndx = headerMap.get(ReservationConstant.PROMO_CODE_ID);
            final int promoOfferIdIndx = headerMap.get(ReservationConstant.PROMO_OFFER_ID);
            final int discountIndx = headerMap.get(ReservationConstant.DISCOUNT);
            final int discountTypeIndx = headerMap.get(ReservationConstant.DISCOUNT_TYPE);
            final int descrIndx = headerMap.get(ReservationConstant.DESCR);
            final int categoryIndx = headerMap.get(ReservationConstant.CATEGORY);

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

            throw new ReservationApplException(ReservationConstant.FILE_NOT_FOUND_LOCATION);
        } catch (IOException e) {

            throw new ReservationApplException(ReservationConstant.ERROR_READING_FILE);
        }
        return promotionalRules;
    }

    private static XSSFSheet getWorkBookSheet(final String resourcePath) throws IOException, FileNotFoundException {
        /*final Resource reservationResource = new ClassPathResource(resourcePath);*/
    	final InputStream inputStream = ProcessReservationData.class.getResourceAsStream(resourcePath);
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
