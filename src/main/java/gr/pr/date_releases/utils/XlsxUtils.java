package gr.pr.date_releases.utils;

public class XlsxUtils {

/*	//need fix to check if series does not exist to insert it, add series from addDate, add notes on seriesEpisodes
	public static List<SeriesEntity> readFromXlsx(File xlsxFile) throws Exception {

		List<SeriesEntity> seriesEpisodesModels = new ArrayList<>();

		FileInputStream fileInputStream = new FileInputStream(xlsxFile);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = xssfWorkbook.getSheetAt(0);
		Iterator<Row> rows = sheet.iterator();

		//Skip Titles
		rows.next();

		while(rows.hasNext()) {
			Row row = rows.next();


			String series = row.getCell(0).getStringCellValue().trim();
			String seasonEpisode = row.getCell(3).getStringCellValue().trim();
			int season, episode;
			Date date = new Date(Calendar.getInstance().getTimeInMillis());
			String notes = null;


			if (row.getCell(5) != null) {
				notes = row.getCell(5).getStringCellValue();
			}


			if (seasonEpisode.length() < 6) {
				season = Integer.valueOf(seasonEpisode.substring(1, 3));
				episode = -1;
			} else {
				season = Integer.valueOf(seasonEpisode.substring(1, 3));
				episode = Integer.valueOf(seasonEpisode.substring(4,6));
			}

			if (row.getCell(4).getCellTypeEnum() == CellType.NUMERIC &&
					DateUtil.isCellDateFormatted(row.getCell(4))) {
				date = new Date(row.getCell(4).getDateCellValue().getTime());
			} else {
				String dateString = row.getCell(4).getStringCellValue().trim();

				if (!dateString.equals("TBA")) {
					date = null;
					if (dateString.substring(3, 5).equals("XX")) {
						if (notes == null) {
							notes = "TBA 20" + dateString.substring(6);
						}
						else{
							notes += ", TBA 20" + dateString.substring(6);
						}
					} else {
						if (notes == null) {
							notes = "TBA " + dateString.substring(3, 5) + "/20" + dateString.substring(6);
						}
						else{
							notes += ", TBA " + dateString.substring(3, 5) + "/20" + dateString.substring(6);
						}
					}
				}
			}
			
			SeriesEntity seriesModel = SeriesTools.getSeriesByName(series);
			SeriesEpisodesModel seriesEpisode = new SeriesEpisodesModel();
			seriesEpisode.setSeason(season);
			seriesEpisode.setEpisode(episode);
			seriesEpisode.setReleaseDate(date);
			seriesEpisode.setNotes(notes);
			seriesEpisode.setSeriesBySeriesId(seriesModel);
			seriesEpisodesModels.add(seriesEpisode);
		}

		return seriesEpisodesModels;
	}*/
}
