package sales;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class SalesAppTest {

//	@Test
//	public void testGenerateReport() {
//
//		SalesApp salesApp = new SalesApp();
//		salesApp.generateSalesActivityReport("DUMMY", 1000, false, false);
//
//	}

	@Test
	public void testIsToday() {
		//given
		SalesApp spySalesApp = spy(SalesApp.class);
		Sales spySales = spy(Sales.class);
		Date curDate = new Date();
		//when
		doReturn(new Date(curDate.getTime() + 24 * 60 * 60 * 1000)).when(spySales).getEffectiveTo();
		doReturn(new Date(curDate.getTime() - 24 * 60 * 60 * 1000)).when(spySales).getEffectiveFrom();
		boolean isToday = spySalesApp.isToday(spySales);
		//then
		Assert.assertTrue(isToday);

	}

	@Test
	public void testIsNotToday() {
		//given
		SalesApp spySalesApp = spy(SalesApp.class);
		Sales spySales = spy(Sales.class);
		Date curDate = new Date();
		//when
		doReturn(new Date(curDate.getTime() - 24 * 60 * 60 * 1000)).when(spySales).getEffectiveTo();
		doReturn(new Date(curDate.getTime() - 2 * 24 * 60 * 60 * 1000)).when(spySales).getEffectiveFrom();
		boolean isToday = spySalesApp.isToday(spySales);
		//then
		Assert.assertFalse(isToday);

	}

	@Test
	public void testGetReportDataList() {
		//given
		Sales spySales = spy(Sales.class);
		SalesReportData salesReportData = mock(SalesReportData.class);
		SalesReportDao salesReportDao = mock(SalesReportDao.class);
		SalesApp salesApp = new SalesApp();
		List<SalesReportData> salesReportDataList = Arrays.asList(salesReportData);
		ArrayList<SalesReportData> filteredReportDataList = new ArrayList<>();

		//when
		when(salesReportData.getType()).thenReturn("SalesActivity");
		when(salesReportData.isConfidential()).thenReturn(false);
		when(salesReportDao.getReportData(spySales)).thenReturn(salesReportDataList);
		salesApp.getReportDataList(salesReportDao, spySales, false, filteredReportDataList);

		//then
		Assert.assertEquals(1, filteredReportDataList.size());
		Assert.assertEquals("SalesActivity", filteredReportDataList.get(0).getType());
	}

	@Test
	public void testGetFilteredDataList() {
		//given
		SalesApp spySalesApp = spy(SalesApp.class);
		int maxRow = 3;
		SalesReportData salesReportData = spy(SalesReportData.class);
		List<SalesReportData> reportDataList = Arrays.asList(
				salesReportData,
				salesReportData,
				salesReportData);
		//when
		List<SalesReportData> filterRowReportDataList = spySalesApp.getFilteredDataList(reportDataList, maxRow);
		//then
		Assert.assertEquals(3, filterRowReportDataList.size());
	}

	@Test
	public void testUpdateDocument() {
		//given
		List<SalesReportData> salesReportDataList = new ArrayList<>();
		SalesApp spySalesApp = spy(SalesApp.class);
		SalesActivityReport salesActivityReport = mock(SalesActivityReport.class);
		//when
		when(spySalesApp.generateReport(Arrays.asList("Sales ID", "Sales Name", "Activity", "Local Time"), salesReportDataList)).thenReturn(salesActivityReport);
		when(salesActivityReport.toXml()).thenReturn("Test");
		//then
		spySalesApp.uploadDocument(false, salesReportDataList);
		verify(salesActivityReport, times(1)).toXml();

	}
}
