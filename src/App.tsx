import { Navigate, Route, Routes } from "react-router-dom";
import { Toaster } from "react-hot-toast";
import { useAuthStore } from "./store/authStore";
import { AuthLayout } from "./layouts/AuthLayout";
import { LoginPage } from "./Login/loginpage";
import { PrivateLayout } from "./layouts/PrivateLayout";
import { RegisterPage } from "./Login/RegisterPage";
import HomePage from "./home/HomePage";


const App = () => {
  const { isAuthenticated } = useAuthStore();

  return (
    <>
      <Routes>
      <Route
          path="/"
          element={
            <PrivateLayout>
              <HomePage />
            </PrivateLayout>
          }
        />
        <Route
          path="/login"
          element={
            isAuthenticated ? (
              <Navigate to={"/"} />
            ) : (
              <AuthLayout>
                <LoginPage />
              </AuthLayout>
            )
          }
        />
        <Route
          path="/register"
          element={
            isAuthenticated ? (
              <Navigate to={"/"} />
            ) : (
              <AuthLayout>
                <RegisterPage />
              </AuthLayout>
            )
          }
        />
      </Routes>

      <Toaster />
    </>
  );
};

export default App;
