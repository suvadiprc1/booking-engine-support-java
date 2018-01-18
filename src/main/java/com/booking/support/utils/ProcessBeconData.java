package com.booking.support.utils;

import com.booking.support.dtos.AssetInfo;
import com.booking.support.dtos.DeviceInformation;
import com.booking.support.dtos.GetBeconRequest;
import com.booking.support.dtos.Location;
import com.booking.support.dtos.PointOfInterest;
import com.booking.support.exceptions.BeconAdditionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProcessBeconData {

    public boolean addBecon(final DeviceInformation deviceInformation) {
        final Map<Location, List<PointOfInterest>> pointOfInterestMap = new HashMap<Location, List<PointOfInterest>>();
        List<PointOfInterest> pointOfInterests = new ArrayList<PointOfInterest>();

        try {
            final InputStream inputStream = ProcessBeconData.class.getResourceAsStream(PointOfInterestConstant.BECON_RESOURCE);
            // Finds the workbook instance for XLSX file
            final XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
            // Return first sheet from the XLSX workbook
            final XSSFSheet sheet = workBook.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum() + 1;
            final Iterator<AssetInfo> iterator = deviceInformation.getDevices().iterator();
            while (iterator.hasNext()) {
                final AssetInfo assetInfo = iterator.next();
                final XSSFRow row = sheet.createRow(lastRowNum++);
                final XSSFCell cell0 = row.createCell(0);
                cell0.setCellValue(assetInfo.getUuid());
                final XSSFCell cell1 = row.createCell(1);
                cell1.setCellValue(assetInfo.getRegion());
                final XSSFCell cell2 = row.createCell(2);
                cell2.setCellValue(assetInfo.getAssetId());
                final XSSFCell cell3 = row.createCell(3);
                cell3.setCellValue(assetInfo.getMessage());
                final XSSFCell cell4 = row.createCell(4);
                cell4.setCellValue(assetInfo.getLongitude());
                final XSSFCell cell5 = row.createCell(5);
                cell5.setCellValue(assetInfo.getLatitude());
                final XSSFCell cell6 = row.createCell(6);
                cell6.setCellValue(assetInfo.getLocationDetails());
            }

            inputStream.close();
            final URL resource = getClass().getResource(PointOfInterestConstant.BECON_RESOURCE);
            FileOutputStream out =
                new FileOutputStream(new File(resource.toURI()));
            workBook.write(out);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new BeconAdditionException("Becon not added", e);
        }
        return true;
    }

    public boolean findBecon(final GetBeconRequest getBeconRequest) {
        boolean isBeaconPresent = false;
        final XSSFSheet workBookSheet;
        try {
            workBookSheet = getWorkBookSheet(PointOfInterestConstant.BECON_RESOURCE);

            final Iterator<Row> iterator = workBookSheet.iterator();
            while (iterator.hasNext()) {
                final Row row = iterator.next();
                final String uuid = row.getCell(0) == null ? null : row.getCell(0).getStringCellValue();
                final String region = row.getCell(1) == null ? null : row.getCell(1).getStringCellValue();
                final String assetId = row.getCell(2) == null ? null : row.getCell(2).getStringCellValue();
                if (StringUtils.isNotBlank(uuid) && StringUtils.isNotBlank(region) && StringUtils.isNotBlank(assetId) && StringUtils
                    .equalsIgnoreCase(getBeconRequest.getUuid(), uuid) && StringUtils
                    .equalsIgnoreCase(getBeconRequest.getRegion(), region) && StringUtils
                    .equalsIgnoreCase(getBeconRequest.getAssetId(), assetId)) {
                    isBeaconPresent = true;
                    break;
                }
            }
            return isBeaconPresent;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BeconAdditionException("Becon search failed!", e);
        }

    }

    private static XSSFSheet getWorkBookSheet(final String resourcePath) throws IOException, FileNotFoundException {
        final InputStream inputStream = ProcessBeconData.class.getResourceAsStream(resourcePath);
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
