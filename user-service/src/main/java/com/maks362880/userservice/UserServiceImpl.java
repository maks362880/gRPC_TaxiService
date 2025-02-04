package com.maks362880.userservice;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import user.User;
import user.UserServiceGrpc;

@GrpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void register(User.RegisterRequest request, StreamObserver<User.RegisterResponse> responseObserver) {
        // Логика регистрации пользователя
        String userId = "12345"; // Здесь вы должны создать пользователя и получить его ID

        User.RegisterResponse response = User.RegisterResponse.newBuilder()
                .setUserId(userId)
                .setStatusCode(User.StatusCode.SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void login(User.LoginRequest request, StreamObserver<User.LoginResponse> responseObserver) {
        // Логика аутентификации пользователя
        String token = "sample_jwt_token"; // Здесь должна быть логика создания JWT токена

        User.LoginResponse response = User.LoginResponse.newBuilder()
                .setToken(token)
                .setStatusCode(User.StatusCode.SUCCESS)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(User.GetUserRequest request, StreamObserver<User.GetUserResponse> responseObserver) {
        // Логика получения пользователя
        String userId = request.getUserId();
        // Здесь нужно извлечь пользователя из базы данных по userId

        User.GetUserResponse response = User.GetUserResponse.newBuilder()
                .setUserId(userId)
                .setUsername("sampleUsername") // Укажите фактическое имя пользователя
                .setEmail("user@example.com") // Укажите фактический email
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}