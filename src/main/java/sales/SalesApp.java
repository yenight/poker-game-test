package sales;

import java.util.*;

public class SalesApp {

	public void generateSalesActivityReport(String salesId, int maxRow, boolean isNatTrade, boolean isSupervisor) {
		
		SalesDao salesDao = new SalesDao();
		SalesReportDao salesReportDao = new SalesReportDao();
		
		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();
		
		if (salesId == null) {
			return;
		}
		
		Sales sales = salesDao.getSalesBySalesId(salesId);
		

		if (this.isToday(sales)) {
			return;
		}
		
		List<SalesReportData> reportDataList = this.getReportDataList(salesReportDao, sales, isSupervisor, filteredReportDataList);
		
		filteredReportDataList = this.getFilteredDataList(reportDataList, maxRow);
		
		this.uploadDocument(isNatTrade, reportDataList);
		
	}

	protected SalesActivityReport generateReport(List<String> headers, List<SalesReportData> reportDataList) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isToday(Sales sales) {
		Date today = new Date();
		if (today.after(sales.getEffectiveTo())
				|| today.before(sales.getEffectiveFrom())){
			return false;
		}
		return true;
	}

	public List<SalesReportData> getReportDataList(SalesReportDao salesReportDao, Sales sales, boolean isSupervisor, List<SalesReportData> filteredReportDataList) {
		List<SalesReportData> reportDataList = salesReportDao.getReportData(sales);

		for (SalesReportData data : reportDataList) {
			if ("SalesActivity".equalsIgnoreCase(data.getType())) {
				if (data.isConfidential()) {
					if (isSupervisor) {
						filteredReportDataList.add(data);
					}
				}else {
					filteredReportDataList.add(data);
				}
			}
		}
		return reportDataList;
	}

	public List<SalesReportData> getFilteredDataList(List<SalesReportData> reportDataList, int maxRow) {
		List<SalesReportData> tempList = new ArrayList<SalesReportData>();
		List<SalesReportData> filteredReportDataList = new ArrayList<SalesReportData>();
		for (int i=0; i < reportDataList.size() || i < maxRow; i++) {
			tempList.add(reportDataList.get(i));
		}
		filteredReportDataList = tempList;
		return filteredReportDataList;

	}

	public void uploadDocument(boolean isNatTrade, List<SalesReportData> reportDataList) {
		List<String> headers = null;
		if (isNatTrade) {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Time");
		} else {
			headers = Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time");
		}

		SalesActivityReport report = this.generateReport(headers, reportDataList);

		EcmService ecmService = new EcmService();
		ecmService.uploadDocument(report.toXml());
	}

}
