package com.dev.smartparking.domain.usecase

import com.dev.smartparking.data.repository.AuthRepository
import com.dev.smartparking.data.repository.BookingRepository
import com.dev.smartparking.data.utils.Result
import com.dev.smartparking.domain.model.Booking
import com.dev.smartparking.domain.model.CreateBooking
import com.dev.smartparking.domain.model.LoginUser
import com.dev.smartparking.domain.model.TicketBooking

class BookingUseCase(private val bookingRepository: BookingRepository) {
    suspend fun createBooking(
        vehicleId: Int,
        slotId: Int,
        placeId: Int,
        scheduledEntry: String,
        scheduledExit: String,
        promoCodeId: String? = null
    ): Result<CreateBooking> {
        val result = bookingRepository.createBooking(
            vehicleId = vehicleId,
            slotId = slotId,
            placeId = placeId,
            scheduledEntry = scheduledEntry,
            scheduledExit = scheduledExit,
            promoCodeId = promoCodeId
        )

        return when (result) {
            is Result.Success -> {
                val createBokingData = result.data.data
                Result.Success(
                    CreateBooking(
                        id = createBokingData.id,
                        bookingReference = createBokingData.bookingReference
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun getBooking(
        bookingId: Int,
    ): Result<Booking> {
        val result = bookingRepository.getBooking(
            bookingId = bookingId
        )

        return when (result) {
            is Result.Success -> {
                val bookingData = result.data.data
                Result.Success(
                    Booking(
                        id = bookingData.id,
                        qrCode = bookingData.qrCode,
                        bookingReference = bookingData.bookingReference,
                        name = bookingData.name,
                        phoneNumber = bookingData.phoneNumber,
                        vehicle = bookingData.vehicle,
                        licencePlate = bookingData.licencePlate,
                        location = bookingData.location,
                        address = bookingData.address,
                        slotNumber = bookingData.slotNumber,
                        startTime = bookingData.startTime,
                        endTime = bookingData.endTime,
                        estimatedPrice = bookingData.estimatedPrice,
                        adminFee = bookingData.adminFee,
                        discount = bookingData.discount,
                        payment = bookingData.payment,
                        status = bookingData.status
                    )
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }

    suspend fun getListTicketBooking(): Result<List<TicketBooking>> {
        return when (val result = bookingRepository.getListTicketBooking()) {
            is Result.Success -> {
                val ticketBookingData = result.data.data
                val ticketBookings = ticketBookingData.map {
                    TicketBooking(
                        id = it.id,
                        licencePlate = it.licencePlate,
                        location = it.location,
                        payment = it.payment,
                        slotNumber = it.slotNumber,
                        startTime = it.startTime,
                        endTime = it.endTime,
                        status = it.status,
                        type = it.type,
                        vehicle = it.vehicle,
                    )
                }
                Result.Success(
                    ticketBookings
                )
            }

            is Result.Error -> Result.Error(result.exception)
            Result.Loading -> Result.Loading
        }
    }
}