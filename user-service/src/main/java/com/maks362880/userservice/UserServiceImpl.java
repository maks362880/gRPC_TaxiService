package com.maks362880.userservice;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import com.maks362880.user_service.proto.UserServiceGrpc;
import com.maks362880.user_service.proto.RegisterRequest;
import com.maks362880.user_service.proto.RegisterResponse;
import com.maks362880.user_service.proto.StatusCode;
import com.maks362880.user_service.proto.LoginResponse;
import com.maks362880.user_service.proto.LoginRequest;
import com.maks362880.user_service.proto.GetUserRequest;
import com.maks362880.user_service.proto.GetUserResponse;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
        // Логика регистрации пользователя
        String userId = "12345"; // Здесь вы должны создать пользователя и получить его ID

        RegisterResponse response = RegisterResponse.newBuilder()
                .setUserId(userId)
                .setStatusCode(StatusCode.SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        // Логика аутентификации пользователя
        String token = "sample_jwt_token"; // Здесь должна быть логика создания JWT токена

        LoginResponse response = LoginResponse.newBuilder()
                .setToken(token)
                .setStatusCode(StatusCode.SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<GetUserResponse> responseObserver) {
        // Логика получения пользователя
        String userId = request.getUserId();
        // Здесь нужно извлечь пользователя из базы данных по userId

        GetUserResponse response = GetUserResponse.newBuilder()
                .setUserId(userId)
                .setUsername("sampleUsername") // Укажите фактическое имя пользователя
                .setEmail("user@example.com") // Укажите фактический email
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}