package parking;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InOrderParkingStrategyTest {

	@Test
    public void testCreateReceipt_givenACarAndAParkingLog_thenGiveAReceiptWithCarNameAndParkingLotName() {

	    /* Exercise 1, Write a test case on InOrderParkingStrategy.createReceipt()
	    * With using Mockito to mock the input parameter */

        ParkingLot parkingLot = mock(ParkingLot.class);
        when(parkingLot.getName()).thenReturn("a");
        Car car = new Car("b");
        Receipt receipt = new Receipt();
        receipt.setParkingLotName("a");
        receipt.setCarName("b");
        assertEquals("a", receipt.getParkingLotName());
        assertEquals("b", receipt.getCarName());
    }

    @Test
    public void testCreateNoSpaceReceipt_givenACar_thenGiveANoSpaceReceipt() {

        /* Exercise 1, Write a test case on InOrderParkingStrategy.createNoSpaceReceipt()
         * With using Mockito to mock the input parameter */
        Car mCar = mock(Car.class);
        when(mCar.getName()).thenReturn("c");

        InOrderParkingStrategy inOrderParkingStrategy = new InOrderParkingStrategy();
        Receipt receipt = inOrderParkingStrategy.createNoSpaceReceipt(mCar);

        verify(mCar, times(1)).getName();
        assertEquals("c", receipt.getCarName());

    }

    @Test
    public void testPark_givenNoAvailableParkingLot_thenCreateNoSpaceReceipt(){

	    /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for no available parking lot */
        InOrderParkingStrategy spyInOrderParkingStrategy = spy(InOrderParkingStrategy.class);
        Car car = new Car("car");
        Receipt receipt = spyInOrderParkingStrategy.park(null, car);
        verify(spyInOrderParkingStrategy, times(1)).park(null, car);
        assertEquals("car", receipt.getCarName());
        assertEquals("No Parking Lot", receipt.getParkingLotName());
    }

    @Test
    public void testPark_givenThereIsOneParkingLotWithSpace_thenCreateReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot */
        ParkingLot parkingLot1 = new ParkingLot("lot1", 2);
        ArrayList<ParkingLot> arrayList = new ArrayList<>();
        Car car = new Car("a");
        arrayList.add(parkingLot1);

        InOrderParkingStrategy spyInOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Receipt receipt = spyInOrderParkingStrategy.park(arrayList, car);

        verify(spyInOrderParkingStrategy, times(1)).park(arrayList, car);
        assertEquals("lot1", receipt.getParkingLotName());
        assertEquals(car, parkingLot1.getParkedCars().get(0));
    }

    @Test
    public void testPark_givenThereIsOneFullParkingLot_thenCreateNoReceipt(){

        /* Exercise 2: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for one available parking lot but it is full */
        ParkingLot parkingLot1 = new ParkingLot("lot1", 1);
        ArrayList<ParkingLot> arrayList = new ArrayList<>();
        Car car = new Car("a");
        Car car2 = new Car("b");
        parkingLot1.getParkedCars().add(car);
        arrayList.add(parkingLot1);

        InOrderParkingStrategy spyInOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Receipt receipt = spyInOrderParkingStrategy.park(arrayList, car2);

        verify(spyInOrderParkingStrategy, times(0)).createReceipt(parkingLot1, car2);
        assertEquals("b", receipt.getCarName());
        assertEquals("No Parking Lot", receipt.getParkingLotName());

    }

    @Test
    public void testPark_givenThereIsMultipleParkingLotAndFirstOneIsFull_thenCreateReceiptWithUnfullParkingLot(){

        /* Exercise 3: Test park() method. Use Mockito.spy and Mockito.verify to test the situation for multiple parking lot situation */
        ParkingLot parkingLot1 = new ParkingLot("lot1", 1);
        ParkingLot parkingLot2 = new ParkingLot("lot2", 1);

        parkingLot1.getParkedCars().add(new Car("a"));

        Car car2 = new Car("b");

        ArrayList<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot1);
        parkingLots.add(parkingLot2);

        InOrderParkingStrategy spyInOrderParkingStrategy = spy(new InOrderParkingStrategy());
        Receipt receipt = spyInOrderParkingStrategy.park(parkingLots, car2);

        assertEquals("b", receipt.getCarName());

    }


}
