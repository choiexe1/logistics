const fetchMessageResolver = () => {
    const language = navigator.language;
    const messages = {
        fetch: {
            ko: {
                success: "변경사항이 성공적으로 저장되었습니다.",
                fail: "변경 중 오류가 발생했습니다. 다시 시도해주세요.",
                networkError: "네트워크 오류가 발생했습니다. 다시 시도해주세요."
            },
            en: {
                success: "Your changes have been successfully saved.",
                fail: "An error occurred during the change, please try again.",
                networkError: "There was a network error, please try again."
            }
        }
    }

    if (language === "ko") return messages.fetch.ko;
    else return messages.fetch.en;
}